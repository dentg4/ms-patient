package com.codigo.clinica.mspaciente.infrastructure.client;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.DoctorDto;
import com.codigo.clinica.mspaciente.infrastructure.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-staff", configuration = FeignConfig.class)
public interface ClientMsStaff {

    @GetMapping("/api/v1/ms-staff/doctor/find/{id}")
    ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long id);
}
