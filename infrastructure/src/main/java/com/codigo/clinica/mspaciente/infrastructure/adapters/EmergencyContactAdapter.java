package com.codigo.clinica.mspaciente.infrastructure.adapters;

import com.codigo.clinica.mspaciente.domain.aggregates.constants.Constants;
import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.EmergencyContactRequest;
import com.codigo.clinica.mspaciente.domain.ports.out.EmergencyContactServiceOut;
import com.codigo.clinica.mspaciente.infrastructure.dao.EmergencyContactRepository;
import com.codigo.clinica.mspaciente.infrastructure.dao.PatientRepository;
import com.codigo.clinica.mspaciente.infrastructure.entity.EmergencyContact;
import com.codigo.clinica.mspaciente.infrastructure.entity.Patient;
import com.codigo.clinica.mspaciente.infrastructure.mapper.EmergencyContactMapper;
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
public class EmergencyContactAdapter implements EmergencyContactServiceOut {

    private final EmergencyContactRepository emergencyContactRepository;
    private final PatientRepository patientRepository;
    private final RedisService redisService;

    @Value("${ms.redis.expiration_time}")
    private int redisExpirationTime;

    @Override
    public EmergencyContactDto createEmergencyContactOut(EmergencyContactRequest request) {
        EmergencyContact emergencyContacts = propertyCreate(request);

        return EmergencyContactMapper.fromEntity(emergencyContactRepository.save(emergencyContacts));
    }

    @Override
    public Optional<EmergencyContactDto> findByIdOut(Long id) {
        String redisInfo =  redisService.getFromRedis(Constants.REDIS_GET_EMERG_CONTACT+id);
        EmergencyContactDto dto;
        if(redisInfo != null){
            dto = Util.convertFromString(redisInfo, EmergencyContactDto.class);
        }else{
            dto = EmergencyContactMapper.fromEntity(findByIdEmergencyContact(id));

            String dataFromRedis = Util.convertToString(dto);
            redisService.saveInRedis(Constants.REDIS_GET_EMERG_CONTACT+id,dataFromRedis,redisExpirationTime);
        }
        return Optional.of(dto);
    }

    @Override
    public List<EmergencyContactDto> getAllOut() {
        List<EmergencyContact> list = emergencyContactRepository.findAll();
        return list.stream().map(EmergencyContactMapper::fromEntity).toList();
    }

    @Override
    public EmergencyContactDto updateOut(Long id, EmergencyContactRequest request) {
        EmergencyContact emergencyContact = findByIdEmergencyContact(id);

        return updateRedis(id, emergencyContactRepository.save(propertyUpdate(emergencyContact, request)));
    }

    @Override
    public EmergencyContactDto deleteOut(Long id) {
        EmergencyContact emergencyContact =  findByIdEmergencyContact(id);

        return updateRedis(id, emergencyContactRepository.save(propertyDelete(emergencyContact)));

    }

    private EmergencyContactDto updateRedis(Long id,EmergencyContact entity){
        EmergencyContactDto emergencyContactDto= EmergencyContactMapper.fromEntity(entity);
        String dataRedis=Util.convertToString(emergencyContactDto);
        redisService.updateInRedis(Constants.REDIS_GET_EMERG_CONTACT+id,dataRedis,redisExpirationTime);
        return emergencyContactDto;
    }

    private EmergencyContact propertyCreate(EmergencyContactRequest request){
        EmergencyContact entity= new EmergencyContact();
        getEntity(entity, request);
        entity.setStatus(Constants.STATUS_ACTIVE);
        entity.setCreatedBy(Constants.USU_ADMIN);
        entity.setCreatedOn(getTimestamp());
        return entity;
    }
    private EmergencyContact propertyUpdate(EmergencyContact entity, EmergencyContactRequest request){
        getEntity(entity,request);
        entity.setUpdatedBy(Constants.USU_ADMIN);
        entity.setUpdatedOn(getTimestamp());
        return entity;
    }
    private EmergencyContact propertyDelete(EmergencyContact entity){
        entity.setStatus(Constants.STATUS_INACTIVE);
        entity.setDeletedBy(Constants.USU_ADMIN);
        entity.setDeletedOn(getTimestamp());
        return entity;
    }
    private void getEntity(EmergencyContact entity,EmergencyContactRequest request) {

        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        entity.setName(request.getName());
        entity.setPhone(request.getPhone());
        entity.setRelation(request.getRelation());
        entity.setPatient(patient);

    }
    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }
    private EmergencyContact findByIdEmergencyContact(Long id){
        return emergencyContactRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Emergency not found"));
    }
}
