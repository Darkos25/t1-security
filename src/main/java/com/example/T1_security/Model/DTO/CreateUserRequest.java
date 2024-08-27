package com.example.T1_security.Model.DTO;

import com.example.T1_security.Model.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for create user")
public class CreateUserRequest {
    private String username;
    private String password;
    private Role role;
}
