package com.example.carsharing.service;

import com.carsharing.model.User;
import com.carsharing.service.UserService;
import com.carsharing.service.impl.CustomUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User.UserBuilder;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.core.userdetails.User.withUsername;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {
    private User testUser;
    private UserBuilder userBuilder;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserService userService;

    @BeforeEach
    void init() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setEmail("testEmail");
        testUser.setPassword("testPassword");
        testUser.setFirstName("firstName");
        testUser.setSecondName("secondName");
        testUser.setRole(User.Role.CUSTOMER);
    }

    @Test
    void loadUserByUsername_ok() {
        Mockito.when(userService.findByEmail(any(String.class))).thenReturn(testUser);
        userBuilder = withUsername(testUser.getEmail());
        userBuilder.password(testUser.getPassword());
        userBuilder.roles(testUser.getRole().name());
        Assertions.assertEquals(customUserDetailsService.loadUserByUsername(testUser.getEmail()), userBuilder.build());
    }
}
