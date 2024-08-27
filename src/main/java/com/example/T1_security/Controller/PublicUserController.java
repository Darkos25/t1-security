package com.example.T1_security.Controller;

import com.example.T1_security.Model.DTO.CreateUserRequest;
import com.example.T1_security.Model.User;
import com.example.T1_security.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/public/user")
@RequiredArgsConstructor
public class PublicUserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody CreateUserRequest request) {
        if(userService.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        } else {
            userService.createUser(User.builder()
                    .username(request.getUsername())
                    .role(request.getRole())
                    .password(passwordEncoder.encode(request.getPassword())).build());
            return ResponseEntity.ok("User created");
        }
    }
}
