package com.codigo.clinica.mspaciente.domain.ports.out;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.StoryDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.StoryRequest;

import java.util.List;
import java.util.Optional;

public interface StoryServiceOut {
    StoryDto createStory(StoryRequest request);
    Optional<StoryDto> findByIdOut(Long id);
    List<StoryDto> getAllOut();
    StoryDto updateOut(Long id, StoryRequest request);
    StoryDto deleteOut(Long id);
}
