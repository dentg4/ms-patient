package com.codigo.clinica.mspaciente.domain.aggregates.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Builder
public class TeatmentDto {
    private Long id;
    private String description;
    private double cost;
    private Date startDate;
    private Date endDate;
    private PatientDto patient;
    private Integer status;
    private String createdBy;
    private Timestamp createOn;
    private String updatedBy;
    private Timestamp updatedOn;
    private String deletedBy;
    private Timestamp deletedOn;
}
