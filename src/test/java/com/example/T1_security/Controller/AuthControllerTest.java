package com.example.T1_security.Controller;

import com.example.T1_security.Config.AuthenticationProviderImpl;
import com.example.T1_security.Config.JwtUtil;
import com.example.T1_security.Model.DTO.auth.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationProviderImpl authenticationProvider;

    @MockBean
    private JwtUtil tokenProvider;

    @Test
    public void authenticateUserShouldReturnJwtToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest("user", "password");
        Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password");

        when(authenticationProvider.authenticate(any())).thenReturn(authentication);
        when(tokenProvider.generateJwtToken(authentication)).thenReturn("mocked-jwt-token");

        mockMvc.perform(post("/api/v1/public/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginRequest))
                        .with(csrf()))  // Добавляем CSRF токен в запрос
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mocked-jwt-token"));
    }
}