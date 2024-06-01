package com.codigo.clinica.mspaciente.domain.aggregates.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmergencyContactRequest {

    @NotBlank(message = "El campo nombre es necesario.")
    private String name;

    @NotBlank(message = "El campo teléfono es necesario.")
    private String phone;

    @NotBlank(message = "El campo relación es necesario.")
    private String relation;

    @NotNull(message = "El campo paciente es necesario.")
    private Long patientId;
}
