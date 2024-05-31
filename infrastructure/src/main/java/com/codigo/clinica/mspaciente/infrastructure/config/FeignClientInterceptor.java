package com.codigo.clinica.mspaciente.infrastructure.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class FeignClientInterceptor implements RequestInterceptor {

    @Value("${token.api_peru}")
    private String tokenReniec;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String url = requestTemplate.feignTarget().url();
        if(url.contains("api.apis.net.pe")){
            requestTemplate.header("Authorization", "Bearer " + tokenReniec);
        }else{
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                String authorization = requestAttributes.getRequest().getHeader("Authorization");
                if(authorization != null && authorization.startsWith("Bearer ")){
                    requestTemplate.header("Authorization", authorization);
                }
            }
        }
    }
}
