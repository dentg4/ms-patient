package com.codigo.clinica.mspaciente.domain.ports.in;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.StoryDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.StoryRequest;

import java.util.List;
import java.util.Optional;

public interface StoryServiceIn {
    StoryDto createStoryIn(StoryRequest request);
    Optional<StoryDto> findByIdIn(Long id);
    List<StoryDto> getAllIn();
    StoryDto updateIn(Long id, StoryRequest request);
    StoryDto deleteIn(Long id);
}
