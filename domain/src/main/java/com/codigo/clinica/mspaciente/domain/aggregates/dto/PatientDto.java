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
public class PatientDto {
    private Long idPatient;
    private String name;
    private String surname;
    private String identificationType;
    private String identificationNumber;
    private LocalDate birthDate;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private String allergies;
    private Integer status;
    private String createdBy;
    private Timestamp createOn;
    private String updatedBy;
    private Timestamp updatedOn;
    private String deletedBy;
    private Timestamp deletedOn;
    private List<EmergencyContactDto> emergencyContacts;
    private List<MedicalRecordDto> stories;
    private List<TeatmentDto> teatments;
}
