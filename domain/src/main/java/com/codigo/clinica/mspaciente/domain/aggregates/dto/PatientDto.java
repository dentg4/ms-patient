package com.codigo.clinica.mspaciente.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDto {
    private Long idPatient;
    private String name;
    private String surname;
    private String identificationType;
    private String identificationNumber;
    private Date birthDate;
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
    private List<MedicalRecordDto> medicalRecords;
    private List<TeatmentDto> teatments;
}
