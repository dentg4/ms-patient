package com.codigo.clinica.mspaciente.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmergencyContactDto {
    private Long id;
    private String name;
    private String phone;
    private String relation;
    private Long patientId;
    private Integer status;
    private String createdBy;
    private Timestamp createOn;
    private String updatedBy;
    private Timestamp updatedOn;
    private String deletedBy;
    private Timestamp deletedOn;
}

