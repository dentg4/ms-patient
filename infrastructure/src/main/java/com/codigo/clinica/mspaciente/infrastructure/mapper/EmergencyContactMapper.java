package com.codigo.clinica.mspaciente.infrastructure.mapper;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDto;
import com.codigo.clinica.mspaciente.infrastructure.entity.EmergencyContact;

public class EmergencyContactMapper {
    public static EmergencyContactDto fromEntity(EmergencyContact entity){
        return EmergencyContactDto.builder()
                .id(entity.getId())
                .phone(entity.getPhone())
                .relation(entity.getRelation())
                .name(entity.getName())
                .patientId(entity.getPatient()==null?null:entity.getPatient().getId())
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
