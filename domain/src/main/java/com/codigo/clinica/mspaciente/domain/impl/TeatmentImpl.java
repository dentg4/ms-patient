package com.codigo.clinica.mspaciente.domain.impl;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.TeatmentDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.TeatmentRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.TeatmentServiceIn;
import com.codigo.clinica.mspaciente.domain.ports.out.TeatmentServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeatmentImpl implements TeatmentServiceIn {

    private final TeatmentServiceOut teatmentServiceOut;

    @Override
    public TeatmentDto createStoryIn(TeatmentRequest request) {
        return teatmentServiceOut.createTeatment(request);
    }

    @Override
    public Optional<TeatmentDto> findByIdIn(Long id) {
        return teatmentServiceOut.findByIdOut(id);
    }

    @Override
    public List<TeatmentDto> getAllIn() {
        return teatmentServiceOut.getAllOut();
    }

    @Override
    public TeatmentDto updateIn(Long id, TeatmentRequest request) {
        return teatmentServiceOut.updateOut(id, request);
    }

    @Override
    public TeatmentDto deleteIn(Long id) {
        return teatmentServiceOut.deleteOut(id);
    }
}
