package com.codigo.clinica.mspaciente.infraestructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "teatments")
@Getter
@Setter
public class Teatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Min(0)
    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

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
