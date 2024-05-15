package com.codigo.clinica.mspaciente.domain.ports.in;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.StoryDTO;
import com.codigo.clinica.mspaciente.domain.aggregates.request.StoryRequest;

import java.util.List;
import java.util.Optional;

public interface StoryServiceIn {
    StoryDTO crearStoryIn(StoryRequest storyRequest);
    Optional<StoryDTO> buscarPorIdIn(Long id);
    List<StoryDTO> obtenerTodosIn();
    StoryDTO actualizarIn(Long id, StoryRequest storyRequest);
    StoryDTO deleteIn(Long id);
}
