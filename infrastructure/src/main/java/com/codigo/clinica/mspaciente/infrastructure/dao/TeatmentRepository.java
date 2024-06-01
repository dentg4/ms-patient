package com.codigo.clinica.mspaciente.infrastructure.dao;

import com.codigo.clinica.mspaciente.infrastructure.entity.Teatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeatmentRepository extends JpaRepository<Teatment, Long> {
}
