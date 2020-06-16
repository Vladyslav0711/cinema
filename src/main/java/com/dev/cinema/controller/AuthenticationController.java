package com.dev.cinema.controller;

import com.dev.cinema.model.dto.request.UserRegistrationDto;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.UserService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService,
                                    UserService userService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registration")
    public void registration(@RequestBody @Valid UserRegistrationDto
                                              userRegistrationDto) {
        authenticationService.register(userRegistrationDto.getEmail(),
                userRegistrationDto.getPassword());
    }
}
