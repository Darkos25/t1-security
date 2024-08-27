package com.example.T1_security.Controller;

import com.example.T1_security.Model.DTO.CreateUserRequest;
import com.example.T1_security.Model.Role;
import com.example.T1_security.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PublicUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void addUserShouldCreateUserWhenUsernameDoesNotExist() throws Exception {
        CreateUserRequest request = new CreateUserRequest("newuser", "password", Role.USER);

        when(userService.existsByUsername("newuser")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encoded-password");

        mockMvc.perform(post("/api/v1/public/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("User created"));
    }

    @Test
    public void addUserShouldReturnBadRequestWhenUsernameExists() throws Exception {
        CreateUserRequest request = new CreateUserRequest("existinguser", "password", Role.USER);

        when(userService.existsByUsername("existinguser")).thenReturn(true);

        mockMvc.perform(post("/api/v1/public/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username already exists"));
    }
}