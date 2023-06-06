package com.carsharing.service;

import com.carsharing.model.User;

public interface UserService {
    User get(Long id);

    User save(User user);

    void update(User user);

    void delete(User user);

    User findByEmail(String email);
}
