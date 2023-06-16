package com.carsharing.controller;

import com.carsharing.model.User;
import com.carsharing.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inject")
public class InjectController {
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Inject user with the MANAGER role")
    public String injectData() {
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setFirstName("admin");
        user.setSecondName("admin");
        user.setPassword("12345678");
        user.setRole(User.Role.MANAGER);
        userService.save(user);
        return "Inject was success!";
    }
}
