package com.codigo.clinica.mspaciente.domain.aggregates.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StoryRequest {
    private String condition;
    private String observations;
    private Long patientId;
}
