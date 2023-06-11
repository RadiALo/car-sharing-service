package com.carsharing.service.impl;

import static org.springframework.security.core.userdetails.User.withUsername;

import com.carsharing.model.User;
import com.carsharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        UserBuilder userBuilder;
        userBuilder = withUsername(email);
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRole().name());
        return userBuilder.build();
    }
}
