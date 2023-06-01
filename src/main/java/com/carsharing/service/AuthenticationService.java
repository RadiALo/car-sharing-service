package com.carsharing.service;

import com.carsharing.model.User;

public interface AuthenticationService {
    User register(User user);

    User login(String email, String password);
}
