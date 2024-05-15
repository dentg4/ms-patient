package com.codigo.clinica.mspaciente.infraestructure.dao;

import com.codigo.clinica.mspaciente.infraestructure.entity.StoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<StoriesEntity, Long> {
}
