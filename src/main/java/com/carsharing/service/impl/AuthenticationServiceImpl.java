package com.carsharing.service.impl;

import com.carsharing.model.Role;
import com.carsharing.model.User;
import com.carsharing.service.AuthenticationService;
import com.carsharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;

    @Override
    public User register(User user) {
        user.setRole(Role.CUSTOMER);
        userService.save(user);
        return user;
    }

    @Override
    public User login(String email, String password) {
        User user = userService.findByEmail(email).orElseThrow();
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new RuntimeException();
        }
    }
}
