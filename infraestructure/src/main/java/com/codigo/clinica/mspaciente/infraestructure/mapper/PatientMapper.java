package com.codigo.clinica.mspaciente.infraestructure.mapper;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.PatientDto;
import com.codigo.clinica.mspaciente.infraestructure.entity.Patient;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PatientMapper {
    public static PatientDto fromEntity(Patient entity){
        return PatientDto.builder()
                .idPatient(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .identificationType(entity.getIdentificationType())
                .identificationNumber(entity.getIdentificationNumber())
                .birthDate(entity.getBirthDate())
                .gender(entity.getGender())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .allergies(entity.getAllergies())
                .emergencyContacts(mapList(entity.getEmergencyContacts(),EmergencyContactMapper::fromEntity))
                .medicalRecords(mapList(entity.getMedicalRecords(),MedicalRecordMapper::fromEntity))
                .teatments(mapList(entity.getTeatments(),TeatmentMapper::fromEntity))
                .status(entity.getStatus())
                .createdBy(entity.getCreatedBy())
                .createOn(entity.getCreatedOn())
                .updatedBy(entity.getUpdatedBy())
                .updatedOn(entity.getUpdatedOn())
                .deletedBy(entity.getDeletedBy())
                .deletedOn(entity.getDeletedOn())
                .build();
    }


    public static  <T, R> List<R> mapList(List<T> list, Function<T, R> mapper) {

        return list != null
                ? list.stream().map(mapper).collect(Collectors.toList())
                : Collections.emptyList();
    }

}
