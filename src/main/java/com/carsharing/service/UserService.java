package com.carsharing.service;

import com.carsharing.model.User;

import java.util.Optional;

public interface UserService {
    User get(Long id);

    User save(User user);

    void update(User user);

    void delete(User user);

    Optional<User> findByEmail(String email);
}
