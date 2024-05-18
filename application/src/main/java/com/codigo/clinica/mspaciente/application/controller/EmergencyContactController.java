package com.codigo.clinica.mspaciente.application.controller;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.EmergencyContactRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.EmergencyContactServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/emergency/contact")
@AllArgsConstructor
public class EmergencyContactController {

    private final EmergencyContactServiceIn emergencyContactServiceIn;

    @PostMapping
    public ResponseEntity<EmergencyContactDto> createEmergencyContact(@RequestBody EmergencyContactRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(emergencyContactServiceIn.createEmergencyContactIn(request));
    }
}
