package com.codigo.clinica.mspaciente.domain.aggregates.request;

import java.time.LocalDate;

public class PatientRequest {
    private String numeroDoc;
    private LocalDate birthDate;
    private String gender;
    private String phone;
    private String email;
}
