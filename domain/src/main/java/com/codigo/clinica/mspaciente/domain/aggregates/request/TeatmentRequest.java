package com.codigo.clinica.mspaciente.domain.aggregates.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TeatmentRequest {
    private String description;
    private double cost;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long patientId;
}
