package com.codigo.clinica.mspaciente.domain.impl;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.TeatmentDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.TeatmentRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.TeatmentServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeatmentImpl implements TeatmentServiceIn {
    @Override
    public TeatmentDto createStoryIn(TeatmentRequest request) {
        return null;
    }

    @Override
    public Optional<TeatmentDto> findByIdIn(Long id) {
        return Optional.empty();
    }

    @Override
    public List<TeatmentDto> getAllIn() {
        return List.of();
    }

    @Override
    public TeatmentDto updateIn(Long id, TeatmentRequest request) {
        return null;
    }

    @Override
    public TeatmentDto deleteIn(Long id) {
        return null;
    }
}
