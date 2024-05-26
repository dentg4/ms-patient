package com.codigo.clinica.mspaciente.domain.aggregates.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class PatientRequest {
    private String identificationType;
    private String identificationNumber;
    private Date birthDate;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private String allergies;
}
