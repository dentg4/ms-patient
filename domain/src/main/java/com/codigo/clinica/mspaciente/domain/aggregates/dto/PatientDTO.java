package com.codigo.clinica.mspaciente.domain.aggregates.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class PatientDTO {
    private Long idPatient;
    private String name;
    private String surname;
    private String numeroDocumento;
    private LocalDate birthDate;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private Integer status;
    private String createdBy;
    private Timestamp createOn;
    private String updatedBy;
    private Timestamp updatedOn;
    private String deletedBy;
    private Timestamp deletedOn;
    private List<EmergencyContactDTO> emergencyContacts;
    private List<StoryDTO> stories;
}
