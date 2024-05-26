package com.codigo.clinica.mspaciente.domain.aggregates.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class TeatmentRequest {
    private String description;
    private double cost;
    private Date startDate;
    private Date endDate;
    private Long patientId;
}
