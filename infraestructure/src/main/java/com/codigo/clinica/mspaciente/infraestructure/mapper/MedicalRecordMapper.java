package com.codigo.clinica.mspaciente.infraestructure.mapper;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.MedicalRecordDto;
import com.codigo.clinica.mspaciente.infraestructure.entity.MedicalRecord;

public class MedicalRecordMapper {
    public static MedicalRecordDto fromEntity(MedicalRecord entity) {
        return MedicalRecordDto.builder()
                .id(entity.getId())
                .diagnos(entity.getDiagnos())
                .observations(entity.getObservations())
                .patientId(entity.getPatient().getId())
                .date(entity.getDate())
                .reference(entity.getReference())
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
