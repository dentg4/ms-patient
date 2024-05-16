package com.codigo.clinica.mspaciente.infraestructure.mapper;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.PatientDTO;
import com.codigo.clinica.mspaciente.infraestructure.entity.Patient;

public class PatientMapper {
    public static PatientDTO fromEntity(Patient entity){
        return PatientDTO.builder()
                .idPatient(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .numeroDocumento(entity.getIdentificationNumber())
                .birthDate(entity.getBirthDate())
                .gender(entity.getGender())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .status(entity.getStatus())
                .createdBy(entity.getCreatedBy())
                .createOn(entity.getCreatedOn())
                .updatedBy(entity.getUpdatedBy())
                .updatedOn(entity.getUpdatedOn())
                .deletedBy(entity.getDeletedBy())
                .deletedOn(entity.getDeletedOn())
                .build();
    }
}
