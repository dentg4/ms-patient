package com.codigo.clinica.mspaciente.infraestructure.client;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.DoctorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-staff")
public interface ClientMsStaff {

    @GetMapping("/api/v1/ms-staff/doctor/find/{id}")
    DoctorDto getDoctorById(@PathVariable Long id);
}
