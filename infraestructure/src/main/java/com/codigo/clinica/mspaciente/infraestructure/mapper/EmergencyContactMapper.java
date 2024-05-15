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
                .state(entity.getStatus())
                .usuaCrea(entity.getCreatedBy())
                .dateCreate(entity.getCreatedOn())
                .usuaModif(entity.getUpdatedBy())
                .dateModif(entity.getUpdatedOn())
                .usuaDelet(entity.getDeletedBy())
                .dateDelet(entity.getDeletedOn())
                .build();
    }
}
