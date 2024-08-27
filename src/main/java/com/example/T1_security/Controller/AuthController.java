package com.example.T1_security.Controller;

import com.example.T1_security.Config.AuthenticationProviderImpl;
import com.example.T1_security.Config.JwtUtil;
import com.example.T1_security.Model.DTO.auth.JwtResponse;
import com.example.T1_security.Model.DTO.auth.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/auth")
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Auth Management", description = "API for authentication")
public class AuthController {

    @Autowired
    private AuthenticationProviderImpl authenticationProvider;

    @Autowired
    private JwtUtil tokenProvider;

    @PostMapping("/login")
    @Operation(summary = "Authentication", description = "Returns JWT token")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
