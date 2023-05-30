package com.carsharing.service;

import com.carsharing.model.User;

public interface AuthenticationService {
    User register(String email, String password);
    User login(String email, String password);
}
