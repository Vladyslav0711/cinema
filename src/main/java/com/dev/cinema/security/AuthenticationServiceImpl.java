package com.dev.cinema.security;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private HashUtil hashUtil;
    private UserService userService;
    private ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(HashUtil hashUtil, UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.hashUtil = hashUtil;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDb = userService.findByEmail(email);
        if (userFromDb == null) {
            throw new AuthenticationException("Incorrect username or Password");
        }
        if (hashUtil.hashPassword(password, userFromDb.getSalt())
                .equals(userFromDb.getPassword())) {
            return userFromDb;
        }
        throw new AuthenticationException("Incorrect username or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        byte[] salt = hashUtil.getSalt();
        user.setEmail(email);
        user.setSalt(salt);
        String hashedPassword = hashUtil.hashPassword(password, salt);
        user.setPassword(hashedPassword);
        user = userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
