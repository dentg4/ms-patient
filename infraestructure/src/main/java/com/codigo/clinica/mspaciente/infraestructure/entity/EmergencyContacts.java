package com.codigo.clinica.mspaciente.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "emergency_contacts")
@Getter
@Setter
public class EmergencyContacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "name", nullable = false)
    private String phone;

    @Column(name = "relation", nullable = false)
    private String relation;

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

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
