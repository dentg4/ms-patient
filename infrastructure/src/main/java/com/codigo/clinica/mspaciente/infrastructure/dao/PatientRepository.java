package com.codigo.clinica.mspaciente.infrastructure.dao;

import com.codigo.clinica.mspaciente.infrastructure.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByIdentificationNumber(String identificationNumber);
}
