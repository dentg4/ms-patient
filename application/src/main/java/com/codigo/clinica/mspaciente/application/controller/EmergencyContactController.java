package com.codigo.clinica.mspaciente.application.controller;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.EmergencyContactDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.EmergencyContactRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.EmergencyContactServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<EmergencyContactDto>> getAll(){
        return ResponseEntity.ok(emergencyContactServiceIn.getAllIn());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmergencyContactDto> getEmergencyContactById(@PathVariable Long id){
        return ResponseEntity.ok(emergencyContactServiceIn.findByIdIn(id).orElseThrow());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmergencyContactDto> updateEmergencyContact(@PathVariable Long id,
                                                                      @RequestBody EmergencyContactRequest request){
        return ResponseEntity.ok(emergencyContactServiceIn.updateIn(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmergencyContactDto> deleteEmergencyContact(@PathVariable Long id){
        return ResponseEntity.ok(emergencyContactServiceIn.deleteIn(id));
    }

}
