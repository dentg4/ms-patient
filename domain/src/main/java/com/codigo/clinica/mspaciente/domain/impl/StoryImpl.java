package com.codigo.clinica.mspaciente.domain.impl;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.StoryDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.StoryRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.StoryServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StoryImpl implements StoryServiceIn {
    @Override
    public StoryDto createStoryIn(StoryRequest request) {
        return null;
    }

    @Override
    public Optional<StoryDto> findByIdIn(Long id) {
        return Optional.empty();
    }

    @Override
    public List<StoryDto> getAllIn() {
        return List.of();
    }

    @Override
    public StoryDto updateIn(Long id, StoryRequest request) {
        return null;
    }

    @Override
    public StoryDto deleteIn(Long id) {
        return null;
    }
}
