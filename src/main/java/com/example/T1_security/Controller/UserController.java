package com.example.T1_security.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "user controller", description = "API for USER")
public class UserController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public String getUser() {
        return "user";
    }
}
