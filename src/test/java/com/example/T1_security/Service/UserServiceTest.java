package com.example.T1_security.Service;

import com.example.T1_security.Model.Role;
import com.example.T1_security.Model.User;
import com.example.T1_security.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void createUserShouldSaveUser() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("encoded-password");
        user.setRole(Role.USER);

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertEquals("username", createdUser.getUsername());
        assertEquals("encoded-password", createdUser.getPassword());
    }

    @Test
    public void existsByUsernameShouldReturnTrueWhenUsernameExists() {
        when(userRepository.existsByUsername("existinguser")).thenReturn(true);

        boolean exists = userService.existsByUsername("existinguser");

        assertTrue(exists);
    }
}