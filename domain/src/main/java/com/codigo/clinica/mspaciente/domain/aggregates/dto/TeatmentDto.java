package com.codigo.clinica.mspaciente.domain.aggregates.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TeatmentDto {
    private Long id;
    private String description;
    private double cost;
    private LocalDate startDate;
    private LocalDate endDate;
    private PatientDto patient;
    private Integer status;
    private String createdBy;
    private Timestamp createOn;
    private String updatedBy;
    private Timestamp updatedOn;
    private String deletedBy;
    private Timestamp deletedOn;
}
