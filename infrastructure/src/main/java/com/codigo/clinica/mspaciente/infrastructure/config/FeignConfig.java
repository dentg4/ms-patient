package com.codigo.clinica.mspaciente.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public FeignClientInterceptor feignClientInterceptor() {
        return new FeignClientInterceptor();
    }
}
