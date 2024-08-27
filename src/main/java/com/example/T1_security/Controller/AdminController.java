package com.example.T1_security.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Tag(name = "admin controller", description = "API for ADMIN")
public class AdminController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Return admin")
    public String admin() {
        return "admin";
    }
}
