package com.codigo.clinica.mspaciente.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class StoryDTO {
    private Long id;
    private String condition;
    private String observations;
    private PatientDTO patient;
    private Integer state;
    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;
}
