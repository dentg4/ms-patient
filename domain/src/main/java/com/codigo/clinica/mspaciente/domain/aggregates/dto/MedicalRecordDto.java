package com.codigo.clinica.mspaciente.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalRecordDto {
    private Long id;
    private String diagnos;
    private String observations;
    private Long doctorId;
    private DoctorDto doctor;
    private String reference;
    private Timestamp date;
    private Long patientId;
    private Integer status;
    private String createdBy;
    private Timestamp createOn;
    private String updatedBy;
    private Timestamp updatedOn;
    private String deletedBy;
    private Timestamp deletedOn;
}
