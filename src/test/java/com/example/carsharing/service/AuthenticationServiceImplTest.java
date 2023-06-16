package com.example.carsharing.service;

import com.carsharing.exception.AuthenticationException;
import com.carsharing.model.User;
import com.carsharing.service.AuthenticationService;
import com.carsharing.service.UserService;
import com.carsharing.service.impl.AuthenticationServiceImpl;
import com.carsharing.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceImplTest {
    private User testUser;
    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private UserService userService;


    @BeforeEach
    public void init() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setFirstName("testFirstName");
        testUser.setSecondName("testSecondName");
        testUser.setPassword("testPassword");
        testUser.setEmail("testEmail");
        testUser.setRole(User.Role.CUSTOMER);
        authenticationService.register(testUser);
    }

    @Test
    void loginWrongPassword_notOk() {
        Mockito.when(userService.findByEmail(any(String.class))).thenReturn(testUser);
        Assertions.assertThrows(AuthenticationException.class,
                () -> authenticationService.login(testUser.getEmail(), "wrongPassword"));
    }

    @Test
    void loginCorrect_ok() {
        Mockito.when(userService.findByEmail(any(String.class))).thenReturn(testUser);
        Assertions.assertEquals(authenticationService.login(testUser.getEmail(), testUser.getPassword()), testUser);
    }
}
