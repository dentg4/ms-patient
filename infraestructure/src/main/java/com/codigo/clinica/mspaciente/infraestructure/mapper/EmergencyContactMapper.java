package com.codigo.clinica.mspaciente.infraestructure.mapper;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDTO;
import com.codigo.clinica.mspaciente.infraestructure.entity.EmergencyContact;

public class EmergencyContactMapper {
    public static EmergencyContactDTO fromEntity(EmergencyContact entity){
        return EmergencyContactDTO.builder()
                .id(entity.getId())
                .phone(entity.getPhone())
                .relation(entity.getRelation())
                .name(entity.getName())
                .patient(PatientMapper.fromEntity(entity.getPatient()))
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
