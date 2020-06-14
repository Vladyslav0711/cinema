package com.dev.cinema.controller;

import com.dev.cinema.model.dto.mappers.UserMapper;
import com.dev.cinema.model.dto.responce.ResponseUserDto;
import com.dev.cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    public UserController(UserService userService,
                          UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/byemail")
    public ResponseUserDto getUserByEmail(@RequestParam String email) {
        return userMapper.mapToResponseUserDto(userService.findByEmail(email));
    }
}
