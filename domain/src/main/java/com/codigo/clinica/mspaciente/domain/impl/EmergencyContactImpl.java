package com.codigo.clinica.mspaciente.domain.impl;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.EmergencyContactRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.EmergencyContactServiceIn;
import com.codigo.clinica.mspaciente.domain.ports.out.EmergencyContactServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmergencyContactImpl implements EmergencyContactServiceIn {

    private final EmergencyContactServiceOut emergencyContactServiceOut;

    @Override
    public EmergencyContactDto createEmergencyContactIn(EmergencyContactRequest request) {
        return emergencyContactServiceOut.createEmergencyContactOut(request);
    }

    @Override
    public Optional<EmergencyContactDto> findByIdIn(Long id) {
        return emergencyContactServiceOut.findByIdOut(id);
    }

    @Override
    public List<EmergencyContactDto> getAllIn() {
        return emergencyContactServiceOut.getAllOut();
    }

    @Override
    public EmergencyContactDto updateIn(Long id, EmergencyContactRequest request) {
        return emergencyContactServiceOut.updateOut(id, request);
    }

    @Override
    public EmergencyContactDto deleteIn(Long id) {
        return emergencyContactServiceOut.deleteOut(id);
    }
}
