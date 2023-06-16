package com.example.carsharing.service;

import com.carsharing.exception.UserNullIdException;
import com.carsharing.model.User;
import com.carsharing.repository.UserRepository;
import com.carsharing.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    private User testUser;
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        testUser = new User();
        testUser.setId(null);
        testUser.setFirstName("testFirstName");
        testUser.setSecondName("testSecondName");
        testUser.setPassword("testPassword");
        testUser.setEmail("testEmail");
        testUser.setRole(User.Role.CUSTOMER);
    }

    @Test
    void updateUserWithNull_notOk() {
        Assertions.assertThrows(UserNullIdException.class,
                () -> userService.update(testUser));
    }
}
