package com.codigo.clinica.mspaciente.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "numero_documento", nullable = false)
    private String numeroDocumento;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender", nullable = false, length = 15)
    private String gender;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "state", nullable = false)
    private Integer state;

    @Column(name = "usuaCrea")
    private String usuaCrea;

    @Column(name = "dateCreate")
    private Timestamp dateCreate;

    @Column(name = "usuaModif")
    private String usuaModif;

    @Column(name = "dateModif")
    private Timestamp dateModif;

    @Column(name = "usuaDelet")
    private String usuaDelet;

    @Column(name = "dateDelet")
    private Timestamp dateDelet;

    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL)
    private List<EmergencyContactsEntity> emergencyContacts;

    @OneToMany(mappedBy = "stories",cascade = CascadeType.ALL)
    private List<StoriesEntity> stories;
}
