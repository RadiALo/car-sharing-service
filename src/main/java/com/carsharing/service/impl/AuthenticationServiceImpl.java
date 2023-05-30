package com.carsharing.service.impl;

import com.carsharing.model.Role;
import com.carsharing.service.AuthenticationService;
import com.carsharing.model.User;
import com.carsharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.CUSTOMER);
        userService.save(user);
        return user;
    }
}
