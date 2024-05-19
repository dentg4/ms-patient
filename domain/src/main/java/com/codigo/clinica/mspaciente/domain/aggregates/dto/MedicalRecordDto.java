package com.codigo.clinica.mspaciente.domain.aggregates.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class MedicalRecordDto {
    private Long id;
    private String diagnos;
    private String observations;
    //private DoctrorDto doctor;
    private String reference;
    private Timestamp date;
    private PatientDto patient;
    private Integer status;
    private String createdBy;
    private Timestamp createOn;
    private String updatedBy;
    private Timestamp updatedOn;
    private String deletedBy;
    private Timestamp deletedOn;
}
