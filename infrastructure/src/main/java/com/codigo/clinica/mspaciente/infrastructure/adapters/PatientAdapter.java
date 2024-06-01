package com.codigo.clinica.mspaciente.infrastructure.adapters;

import com.codigo.clinica.mspaciente.domain.aggregates.constants.Constants;
import com.codigo.clinica.mspaciente.domain.aggregates.dto.PatientDto;
import com.codigo.clinica.mspaciente.domain.aggregates.dto.ReniecDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.PatientRequest;
import com.codigo.clinica.mspaciente.domain.ports.out.PatientServiceOut;
import com.codigo.clinica.mspaciente.infrastructure.client.ClientReniec;
import com.codigo.clinica.mspaciente.infrastructure.dao.PatientRepository;
import com.codigo.clinica.mspaciente.infrastructure.entity.Patient;
import com.codigo.clinica.mspaciente.infrastructure.mapper.PatientMapper;
import com.codigo.clinica.mspaciente.infrastructure.redis.RedisService;
import com.codigo.clinica.mspaciente.infrastructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientAdapter implements PatientServiceOut {

    private final PatientRepository patientRepository;
    private final RedisService redisService;
    private final ClientReniec clientReniec;

    @Value("${ms.redis.expiration_time}")
    private int redisExpirationTime;

    @Value("${token.api_peru}")
    private String tokenReniec;

    @Override
    public PatientDto createPatientOut(PatientRequest request) {
        Patient patients = propertyCreate(request);

        return PatientMapper.fromEntity(patientRepository.save(patients));
    }

    @Override
    public Optional<PatientDto> findByIdOut(Long id) {
        String redisInfo =  redisService.getFromRedis(Constants.REDIS_GET_PATIENT+id);
        PatientDto dto;
        if(redisInfo != null){
            dto = Util.convertFromString(redisInfo, PatientDto.class);
        }else{
            dto = PatientMapper.fromEntity(findByIdPatient(id));

            String dataFromRedis = Util.convertToString(dto);
            redisService.saveInRedis(Constants.REDIS_GET_PATIENT+id,dataFromRedis,redisExpirationTime);
        }
        return Optional.of(dto);
    }

    @Override
    public List<PatientDto> getAllOut() {
        List<Patient> list = patientRepository.findAll();
        return list.stream().map(PatientMapper::fromEntity).toList();
    }

    @Override
    public PatientDto updateOut(Long id, PatientRequest request) {
        Patient patient = findByIdPatient(id);

        return updateRedis(id, patientRepository.save(propertyUpdate(patient, request)));
    }

    @Override
    public PatientDto deleteOut(Long id) {
        Patient patient =  findByIdPatient(id);

        return updateRedis(id, patientRepository.save(propertyDelete(patient)));

    }

    private PatientDto updateRedis(Long id,Patient entity){
        PatientDto patientDto= PatientMapper.fromEntity(entity);
        String dataRedis=Util.convertToString(patientDto);
        redisService.updateInRedis(Constants.REDIS_GET_PATIENT+id,dataRedis,redisExpirationTime);
        return patientDto;
    }

    public Patient propertyCreate(PatientRequest request){
        Patient entity= new Patient();
        getEntity(entity, request);
        entity.setStatus(Constants.STATUS_ACTIVE);
        entity.setCreatedBy(Constants.USU_ADMIN);
        entity.setCreatedOn(getTimestamp());
        return entity;
    }
    public Patient propertyUpdate(Patient entity, PatientRequest request){
        getEntity(entity,request);
        entity.setUpdatedBy(Constants.USU_ADMIN);
        entity.setUpdatedOn(getTimestamp());
        return entity;
    }
    private Patient propertyDelete(Patient entity){
        entity.setStatus(Constants.STATUS_INACTIVE);
        entity.setDeletedBy(Constants.USU_ADMIN);
        entity.setDeletedOn(getTimestamp());
        return entity;
    }
    private void getEntity(Patient entity,PatientRequest request) {
        ReniecDto reniecDto =  getExecutionReniec(request.getIdentificationNumber());
        entity.setName(reniecDto.getNombres());
        entity.setSurname(reniecDto.getApellidoPaterno()+" "+reniecDto.getApellidoMaterno());
        entity.setIdentificationType(reniecDto.getTipoDocumento());

        entity.setIdentificationNumber(request.getIdentificationNumber());
        entity.setBirthDate(request.getBirthDate());
        entity.setGender(request.getGender());
        entity.setPhone(request.getPhone());
        entity.setAddress(request.getAddress());
        entity.setEmail(request.getEmail());
        entity.setAllergies(request.getAllergies());

    }
    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }
    private ReniecDto getExecutionReniec(String numero){
        String authorization = "Bearer "+tokenReniec;
        return clientReniec.getInfoReniec(numero,authorization);
    }
    private Patient findByIdPatient(Long id){
        return patientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Patient not found"));
    }
}