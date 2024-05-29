package com.codigo.clinica.mspaciente.infrastructure.mapper;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.TeatmentDto;
import com.codigo.clinica.mspaciente.infrastructure.entity.Teatment;

public class TeatmentMapper {
    public static TeatmentDto fromEntity(Teatment entity) {
        return TeatmentDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .cost(entity.getCost())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .patientId(entity.getPatient().getId())
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
