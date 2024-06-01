package com.codigo.clinica.mspaciente.infrastructure.dao;

import com.codigo.clinica.mspaciente.infrastructure.entity.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
}
