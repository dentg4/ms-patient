package com.codigo.clinica.mspaciente.domain.aggregates.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class MedicalRecordRequest {

    @NotBlank(message = "El campo diagnostico es necesario.")
    private String diagnos;

    @NotBlank(message = "El campo observaciones es necesario.")
    private String observations;

    @NotNull(message = "El campo paciente es necesario.")
    private Long patientId;

    @NotNull(message = "El campo doctor es necesario.")
    private Long doctorId;

    @NotBlank(message = "El campo referencia es necesario.")
    private String reference;

    @NotNull(message = "El campo fecha es necesario.")
    private Timestamp date;
}
