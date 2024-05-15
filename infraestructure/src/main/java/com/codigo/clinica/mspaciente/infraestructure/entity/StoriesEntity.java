package com.codigo.clinica.mspaciente.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "stories")
@Getter
@Setter
public class StoriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "condition", nullable = false)
    private String condition;

    @Column(name = "observations")
    private String observations;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

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
}

