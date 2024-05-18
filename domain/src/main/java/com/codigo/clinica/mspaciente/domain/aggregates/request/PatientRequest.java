package com.codigo.clinica.mspaciente.domain.aggregates.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class PatientRequest {
    private String identificationType;
    private String identificationNumber;
    private LocalDate birthDate;
    private String gender;
    private String phone;
    private String email;
    private String allergies;
}
