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
public class PatientRequest {

    @NotBlank(message = "El campo número de indetificación es necesario.")
    private String identificationNumber;

    @NotNull(message = "El campo fecha de nacimiento es necesario.")
    private Date birthDate;

    @NotBlank(message = "El campo género es necesario.")
    private String gender;

    @NotBlank(message = "El campo teléfono es necesario.")
    private String phone;

    @NotBlank(message = "El campo email es necesario.")
    private String email;

    @NotBlank(message = "El campo dirección es necesario.")
    private String address;

    @NotBlank(message = "El campo alergias es necesario.")
    private String allergies;
}
