package com.codigo.clinica.mspaciente.domain.impl;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDTO;
import com.codigo.clinica.mspaciente.domain.aggregates.request.EmergencyContactRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.EmergencyContactServiceIn;
import com.codigo.clinica.mspaciente.domain.ports.out.EmergencyContactServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmergencyContactImpl implements EmergencyContactServiceIn {

    private final EmergencyContactServiceOut emergencyContactServiceOut;
    @Override
    public EmergencyContactDTO crearEmergencyContactIn(EmergencyContactRequest request) {
        return emergencyContactServiceOut.crearEmergencyContactOut(request);
    }

    @Override
    public Optional<EmergencyContactDTO> buscarPorIdIn(Long id) {
        return emergencyContactServiceOut.buscarPorIdOut(id);
    }

    @Override
    public List<EmergencyContactDTO> obtenerTodosIn() {
        return emergencyContactServiceOut.obtenerTodosOut();
    }

    @Override
    public EmergencyContactDTO actualizarIn(Long id, EmergencyContactRequest request) {
        return emergencyContactServiceOut.actualizarOut(id, request);
    }

    @Override
    public EmergencyContactDTO deleteIn(Long id) {
        return emergencyContactServiceOut.deleteOut(id);
    }
}
