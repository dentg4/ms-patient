package com.codigo.clinica.mspaciente.domain.aggregates.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmergencyContactRequest {
    private String name;
    private String phone;
    private String relation;
    private Long id_patient;
}
