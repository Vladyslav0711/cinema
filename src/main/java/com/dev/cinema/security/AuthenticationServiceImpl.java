package com.dev.cinema.security;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;

public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDb = userService.findByEmail(email);
        if (userFromDb == null) {
            throw new AuthenticationException("Incorrect username or Password");
        }
        if (HashUtil.hashPassword(password, userFromDb.getSalt())
                .equals(userFromDb.getPassword())) {
            return userFromDb;
        }
        throw new AuthenticationException("Incorrect username or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userService.add(user);
    }
}
