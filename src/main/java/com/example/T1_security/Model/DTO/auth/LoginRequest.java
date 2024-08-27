package com.example.T1_security.Model.DTO.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Authentication request")
public class LoginRequest {

    @Schema(description = "username", example = "user")
    private String username;
    @Schema(description = "Password", example = "password")
    private String password;
}
