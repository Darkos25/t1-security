package com.example.T1_security;

import com.example.T1_security.Model.Role;
import com.example.T1_security.Model.User;
import com.example.T1_security.Repository.UserRepository;
import jakarta.persistence.PostLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class T1SecurityApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(T1SecurityApplication.class, args);
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@PostLoad
	public void createUserAfterStart() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword(encoder().encode("admin"));
		user.setRole(Role.ADMIN);
		userRepository.save(user);
	}

}
