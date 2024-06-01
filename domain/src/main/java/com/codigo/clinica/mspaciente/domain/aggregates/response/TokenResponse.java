package com.codigo.clinica.mspaciente.domain.aggregates.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TokenResponse {
    private Boolean isValid;
    private List<String> roles;
    private String username;
    private Boolean isTokenExpired;
}
