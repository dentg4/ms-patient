package com.codigo.clinica.mspaciente.infrastructure.adapters;

import com.codigo.clinica.mspaciente.domain.aggregates.constants.Constants;
import com.codigo.clinica.mspaciente.domain.aggregates.dto.TeatmentDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.TeatmentRequest;
import com.codigo.clinica.mspaciente.domain.ports.out.TeatmentServiceOut;
import com.codigo.clinica.mspaciente.infrastructure.dao.PatientRepository;
import com.codigo.clinica.mspaciente.infrastructure.dao.TeatmentRepository;
import com.codigo.clinica.mspaciente.infrastructure.entity.Patient;
import com.codigo.clinica.mspaciente.infrastructure.entity.Teatment;
import com.codigo.clinica.mspaciente.infrastructure.mapper.TeatmentMapper;
import com.codigo.clinica.mspaciente.infrastructure.redis.RedisService;
import com.codigo.clinica.mspaciente.infrastructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeatmentAdapter implements TeatmentServiceOut {

    private final TeatmentRepository teatmentRepository;
    private final PatientRepository patientRepository;
    private final RedisService redisService;

    @Value("${ms.redis.expiration_time}")
    private int redisExpirationTime;

    @Override
    public TeatmentDto createTeatment(TeatmentRequest request) {
        Teatment teatment = getEntity(new Teatment(), request, false, null);
        return TeatmentMapper.fromEntity(teatmentRepository.save(teatment));
    }

    @Override
    public Optional<TeatmentDto> findByIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constants.REDIS_GET_TEATMENT + id);
        TeatmentDto teatmentDto;
        if(redisInfo != null){
            teatmentDto = Util.convertFromString(redisInfo, TeatmentDto.class);
        } else {
            teatmentDto = TeatmentMapper.fromEntity(teatmentRepository.findById(id).orElseThrow(()-> new RuntimeException("Teatment not found")));
            String dataForRedis = Util.convertToString(teatmentDto);
            redisService.saveInRedis(Constants.REDIS_GET_TEATMENT + id, dataForRedis, redisExpirationTime);
        }
        return Optional.of(teatmentDto);
    }

    @Override
    public List<TeatmentDto> getAllOut() {
        List<TeatmentDto> dtoList = new ArrayList<>();
        List<Teatment> entities = teatmentRepository.findAll();
        for (Teatment data : entities){
            dtoList.add(TeatmentMapper.fromEntity(data));
        }
        return dtoList;
    }

    @Override
    public TeatmentDto updateOut(Long id, TeatmentRequest request) {
        Optional<Teatment> extractedData = teatmentRepository.findById(id);
        if(extractedData.isPresent()){
            Teatment teatment = getEntity(extractedData.get(), request,true, id);
            return TeatmentMapper.fromEntity(teatmentRepository.save(teatment));
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public TeatmentDto deleteOut(Long id) {
        Optional<Teatment> teatments = teatmentRepository.findById(id);
        if(teatments.isPresent()){
            teatments.get().setStatus(0);
            teatments.get().setDeletedBy(Constants.USU_ADMIN);
            teatments.get().setDeletedOn(getTimestamp());
            return TeatmentMapper.fromEntity(teatmentRepository.save(teatments.get()));
        } else {
            throw new RuntimeException();
        }
    }

    // Support methods
    private Teatment getEntity(Teatment entity, TeatmentRequest teatmentRequest, boolean updateIf, Long id){
        entity.setDescription(teatmentRequest.getDescription());
        entity.setCost(teatmentRequest.getCost());
        entity.setStartDate(teatmentRequest.getStartDate());
        entity.setEndDate(teatmentRequest.getEndDate());
        entity.setStatus(Constants.STATUS_ACTIVE);

        Patient patient = patientRepository.findById(teatmentRequest.getPatientId()).orElseThrow(()-> new RuntimeException("Patient not found"));
        entity.setPatient(patient);

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
