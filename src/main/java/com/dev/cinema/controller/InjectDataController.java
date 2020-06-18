package com.dev.cinema.controller;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InjectDataController {

    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private ShoppingCartService shoppingCartService;

    public InjectDataController(RoleService roleService,
                                PasswordEncoder passwordEncoder,
                                UserService userService, ShoppingCartService shoppingCartService) {
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostConstruct
    public void injectRoles() {
        roleService.add(Role.of("USER"));
        roleService.add(Role.of("ADMIN"));
    }

    @PostConstruct
    public void injectUser() {
        User user = new User();
        user.setEmail("vlad.bes0711@gmail.com");
        user.setPassword(passwordEncoder.encode("user"));
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        user = userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
    }

    @PostConstruct
    public void injectAdmin() {
        User admin = new User();
        admin.setEmail("vlad.admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        admin = userService.add(admin);
        shoppingCartService.registerNewShoppingCart(admin);
    }

}
