package com.codigo.clinica.mspaciente.infraestructure.adapters;

import com.codigo.clinica.mspaciente.domain.aggregates.constants.Constants;
import com.codigo.clinica.mspaciente.domain.aggregates.dto.DoctorDto;
import com.codigo.clinica.mspaciente.domain.aggregates.dto.MedicalRecordDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.MedicalRecordRequest;
import com.codigo.clinica.mspaciente.domain.ports.out.MedicalRecordServiceOut;
import com.codigo.clinica.mspaciente.infraestructure.client.ClientMsStaff;
import com.codigo.clinica.mspaciente.infraestructure.dao.MedicalRecordRepository;
import com.codigo.clinica.mspaciente.infraestructure.dao.PatientRepository;
import com.codigo.clinica.mspaciente.infraestructure.entity.MedicalRecord;
import com.codigo.clinica.mspaciente.infraestructure.entity.Patient;
import com.codigo.clinica.mspaciente.infraestructure.mapper.MedicalRecordMapper;
import com.codigo.clinica.mspaciente.infraestructure.redis.RedisService;
import com.codigo.clinica.mspaciente.infraestructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicalRecordAdapter implements MedicalRecordServiceOut {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    private final ClientMsStaff clientMsStaff;
    private final RedisService redisService;

    @Value("${ms.redis.expiration_time}")
    private int redisExpirationTime;

    @Override
    public MedicalRecordDto createMedicalRecordOut(MedicalRecordRequest request) {
        MedicalRecord medicalRecord = getEntity(new MedicalRecord(), request, false, null);
        return MedicalRecordMapper.fromEntity(medicalRecordRepository.save(medicalRecord));
    }

    @Override
    public Optional<MedicalRecordDto> findByIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constants.REDIS_GET_MEDICAL_REC + id);
        MedicalRecordDto medicalRecordDto;
        if(redisInfo != null){
            medicalRecordDto = Util.convertFromString(redisInfo, MedicalRecordDto.class);
        }else{
            MedicalRecord medicalRecord = medicalRecordRepository.findById(id).orElseThrow(()-> new RuntimeException("Historial no no encontrado."));
            medicalRecordDto = MedicalRecordMapper.fromEntity(medicalRecord);
            DoctorDto doctorDto = clientMsStaff.getDoctorById(medicalRecord.getDoctorId());
            medicalRecordDto.setDoctor(doctorDto);
            String dataForRedis = Util.convertToString(medicalRecordDto);
            redisService.saveInRedis(Constants.REDIS_GET_MEDICAL_REC + id, dataForRedis, redisExpirationTime);
        }
        return Optional.of(medicalRecordDto);
    }

    @Override
    public List<MedicalRecordDto> getAllOut() {
        List<MedicalRecordDto> dtoList = new ArrayList<>();
        List<MedicalRecord> entities = medicalRecordRepository.findAll();
        for (MedicalRecord data : entities){
            dtoList.add(MedicalRecordMapper.fromEntity(data));
        }
        return dtoList;
    }

    @Override
    public MedicalRecordDto updateOut(Long id, MedicalRecordRequest request) {
        Optional<MedicalRecord> extractedData = medicalRecordRepository.findById(id);
        if(extractedData.isPresent()){
            MedicalRecord medicalRecord = getEntity(extractedData.get(), request,true, id);
            return MedicalRecordMapper.fromEntity(medicalRecordRepository.save(medicalRecord));
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public MedicalRecordDto deleteOut(Long id) {
        Optional<MedicalRecord> extractedData = medicalRecordRepository.findById(id);
        if(extractedData.isPresent()){
            extractedData.get().setStatus(0);
            extractedData.get().setDeletedBy(Constants.USU_ADMIN);
            extractedData.get().setDeletedOn(getTimestamp());
            return MedicalRecordMapper.fromEntity(medicalRecordRepository.save(extractedData.get()));
        }else {
            throw new RuntimeException();
        }
    }

    // Support methods
    private MedicalRecord getEntity(MedicalRecord entity, MedicalRecordRequest medicalRecordRequest, boolean updateIf, Long id){
        entity.setDiagnos(medicalRecordRequest.getDiagnos());
        entity.setObservations(medicalRecordRequest.getObservations());
        entity.setReference(medicalRecordRequest.getReference());
        entity.setDate(medicalRecordRequest.getDate());
        entity.setStatus(Constants.STATUS_ACTIVE);

        Patient patient = patientRepository.findById(medicalRecordRequest.getPatientId()).orElseThrow(()-> new RuntimeException("Paciente no encontrado."));
        entity.setPatient(patient);

        /*
        Doctor doctor = doctorRepository.findById(medicalRecordRequest.getDoctorId()).orElseThrow(()-> new RuntimeException("Doctor no encontrado."));
        entity.setDoctor(doctor);
        */

        if (updateIf) {
            entity.setId(id);
            entity.setUpdatedBy(Constants.USU_ADMIN);
            entity.setUpdatedOn(getTimestamp());
        } else {
            entity.setCreatedBy(Constants.USU_ADMIN);
            entity.setCreatedOn(getTimestamp());
        }
        return entity;
    }

    private Timestamp getTimestamp(){
        long currenTime = System.currentTimeMillis();
        return new Timestamp(currenTime);
    }
}
