package com.codigo.clinica.mspaciente.infraestructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "stories")
@Getter
@Setter
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "condition", nullable = false)
    private String condition;

    @Column(name = "observations")
    private String observations;

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

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}

