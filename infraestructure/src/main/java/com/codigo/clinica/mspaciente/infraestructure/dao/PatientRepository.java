package com.codigo.clinica.mspaciente.infraestructure.dao;

import com.codigo.clinica.mspaciente.infraestructure.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByIdentificationNumber(String identificationNumber);
}
