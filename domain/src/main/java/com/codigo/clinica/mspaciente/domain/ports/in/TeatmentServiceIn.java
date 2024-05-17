package com.codigo.clinica.mspaciente.domain.ports.in;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.TeatmentDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.TeatmentRequest;

import java.util.List;
import java.util.Optional;

public interface TeatmentServiceIn {
    TeatmentDto createStoryIn(TeatmentRequest request);
    Optional<TeatmentDto> findByIdIn(Long id);
    List<TeatmentDto> getAllIn();
    TeatmentDto updateIn(Long id, TeatmentRequest request);
    TeatmentDto deleteIn(Long id);
}
