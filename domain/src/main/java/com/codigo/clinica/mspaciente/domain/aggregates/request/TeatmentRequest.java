package com.codigo.clinica.mspaciente.domain.aggregates.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class TeatmentRequest {

    @NotBlank(message = "El campo description es necesario.")
    private String description;

    @NotNull(message = "El campo costo es necesario.")
    private double cost;

    @NotNull(message = "El campo fecha inicio es necesario.")
    private Date startDate;

    @NotNull(message = "El campo fecha fin es necesario.")
    private Date endDate;

    @NotNull(message = "El campo paciente es necesario.")
    private Long patientId;
}
