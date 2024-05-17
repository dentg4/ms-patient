package com.codigo.clinica.mspaciente.infraestructure.mapper;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.StoryDto;
import com.codigo.clinica.mspaciente.infraestructure.entity.Story;

public class StoryMapper {
    public static StoryDto fromEntity(Story entity) {
        return StoryDto.builder()
                .id(entity.getId())
                .condition(entity.getCondition())
                .observations(entity.getObservations())
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
