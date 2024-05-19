package com.codigo.clinica.mspaciente.application.controller;


import com.codigo.clinica.mspaciente.domain.aggregates.dto.PatientDto;
import com.codigo.clinica.mspaciente.domain.aggregates.request.PatientRequest;
import com.codigo.clinica.mspaciente.domain.ports.in.PatientServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
@AllArgsConstructor
public class PatientController {

    private final PatientServiceIn patientServiceIn;

    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(patientServiceIn.createPatientIn(request));
    }
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAll(){
        return ResponseEntity.ok(patientServiceIn.getAllIn());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id){
        return ResponseEntity.ok(patientServiceIn.findByIdIn(id).orElseThrow());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id,
                                                                      @RequestBody PatientRequest request){
        return ResponseEntity.ok(patientServiceIn.updateIn(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientDto> deletePatient(@PathVariable Long id){
        return ResponseEntity.ok(patientServiceIn.deleteIn(id));
    }

}