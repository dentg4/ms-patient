package com.codigo.clinica.mspaciente.infraestructure.mapper;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDTO;
import com.codigo.clinica.mspaciente.infraestructure.entity.EmergencyContactsEntity;

public class EmergencyContactMapper {
    public static EmergencyContactDTO fromEntity(EmergencyContactsEntity entity){
        return EmergencyContactDTO.builder()
                .id(entity.getId())
                .phone(entity.getPhone())
                .relation(entity.getRelation())
//                .patient(entity.getPatient())
                .state(entity.getState())
                .usuaCrea(entity.getUsuaCrea())
                .dateCreate(entity.getDateCreate())
                .usuaModif(entity.getUsuaModif())
                .dateModif(entity.getDateModif())
                .usuaDelet(entity.getUsuaDelet())
                .dateDelet(entity.getDateDelet())
                .build();
    }
}
