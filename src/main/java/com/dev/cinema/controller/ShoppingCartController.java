package com.dev.cinema.controller;

import com.dev.cinema.model.dto.mappers.ShoppingCartMapper;
import com.dev.cinema.model.dto.responce.ResponseShoppingCartDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    private UserService userService;
    private MovieSessionService movieSessionService;
    private ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService,
                                  ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping("/addmoviesession")
    public void addMovieSession(@RequestParam Long userId,
                                @RequestParam Long movieSessionId) {
        shoppingCartService.addSession(movieSessionService.getById(movieSessionId),
                userService.getById(userId));
    }

    @GetMapping("byuser")
    public ResponseShoppingCartDto getByUserId(@RequestParam Long userId) {
        return shoppingCartMapper.mapToResponseShoppingCartDto(shoppingCartService
                .getByUserId(userId));
    }
}
