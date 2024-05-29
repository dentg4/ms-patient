package com.codigo.clinica.mspaciente.domain.aggregates.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class MedicalRecordRequest {
    private String diagnos;
    private String observations;
    private Long patientId;
    private Long doctorId;
    private String reference;
    private Timestamp date;
}
