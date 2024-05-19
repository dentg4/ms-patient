package com.codigo.clinica.mspaciente.domain.ports.out;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.TeatmentDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.TeatmentRequest;

import java.util.List;
import java.util.Optional;

public interface TeatmentServiceOut {
    TeatmentDto createTeatment(TeatmentRequest request);
    Optional<TeatmentDto> findByIdOut(Long id);
    List<TeatmentDto> getAllOut();
    TeatmentDto updateOut(Long id, TeatmentRequest request);
    TeatmentDto deleteOut(Long id);
}
