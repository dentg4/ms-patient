package com.codigo.clinica.mspaciente.infraestructure.dao;

import com.codigo.clinica.mspaciente.infraestructure.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
