package com.codigo.clinica.mspaciente.infraestructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "surname", nullable = false, length = 200)
    private String surname;

    @Column(name = "identification_type", nullable = false, length = 16)
    private String identificationType;

    @Column(name = "identification_number", nullable = false, length = 15)
    private String identificationNumber;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender", nullable = false, length = 15)
    private String gender;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Email
    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "address", length = 254)
    private String address;

    @Column(name = "allergies", length = 254)
    private String allergies;

    @Min(0) @Max(1)
    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created_by", nullable = false, length = 254)
    private String createdBy;

    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

    @Column(name = "updated_by", length = 254)
    private String updatedBy;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @Column(name = "deleted_by", length = 254)
    private String deletedBy;

    @Column(name = "deleted_on")
    private Timestamp deletedOn;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<EmergencyContact> emergencyContacts;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<MedicalRecord> stories;
}
