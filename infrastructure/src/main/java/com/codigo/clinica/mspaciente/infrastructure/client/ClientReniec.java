package com.codigo.clinica.mspaciente.infrastructure.client;

import com.codigo.clinica.mspaciente.domain.aggregates.dto.ReniecDto;
import com.codigo.clinica.mspaciente.infrastructure.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-reniec", url = "https://api.apis.net.pe/v2/reniec/", configuration = FeignConfig.class)
public interface ClientReniec {
    @GetMapping("/dni")
    ReniecDto getInfoReniec(@RequestParam("numero") String number, @RequestHeader("Authorization") String authorization);
}
