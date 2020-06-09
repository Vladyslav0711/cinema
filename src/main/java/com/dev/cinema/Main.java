package com.dev.cinema;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static final Injector INJECTOR = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService)
                INJECTOR.getInstance(MovieService.class);
        movieService.getAll().forEach(System.out::println);
        Movie movie = new Movie();
        movie.setTittle("Fast and furious");
        movieService.add(movie);
        Movie movie1 = new Movie();
        movie1.setTittle("Deadpool");
        movieService.add(movie1);

        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService = (CinemaHallService)
                INJECTOR.getInstance(CinemaHallService.class);
        cinemaHallService.getAll().forEach(System.out::println);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(50);
        cinemaHall.setDescription("Cinema hall - A");
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.now().plusDays(1));
        MovieSessionService movieSessionService = (MovieSessionService)
                INJECTOR.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(movie.getId(),
                LocalDate.now()).forEach(System.out::println);
        MovieSession movieSession1 = new MovieSession();
        movieSession1.setMovie(movie1);
        movieSession1.setCinemaHall(cinemaHall);
        movieSession1.setShowTime(LocalDateTime.now().plusDays(1));
        movieSessionService.add(movieSession1);

        AuthenticationService authenticationService = (AuthenticationService)
                INJECTOR.getInstance(AuthenticationService.class);
        authenticationService.register("vlad@gmail.com", "vlad");
        User user;
        try {
            System.out.println(authenticationService.login("vlad@gmail.com", "vlad"));
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
        authenticationService.register("ola@gmail.com", "ola12");
        try {
            user = authenticationService.login("ola@gmail.com", "ola12");
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }

        ShoppingCartService shoppingCartService = (ShoppingCartService)
                INJECTOR.getInstance(ShoppingCartService.class);
        shoppingCartService.registerNewShoppingCart(user);
        shoppingCartService.addSession(movieSession, user);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        System.out.println(shoppingCartService.getByUser(user));

        OrderService orderService = (OrderService)
                INJECTOR.getInstance(OrderService.class);
        List<Ticket> tickets = List.copyOf(shoppingCart.getTickets());
        orderService.completeOrder(tickets, user);
        shoppingCartService.clear(shoppingCart);
        shoppingCartService.addSession(movieSession1, user);
        shoppingCart = shoppingCartService.getByUser(user);
        tickets = List.copyOf(shoppingCart.getTickets());
        orderService.completeOrder(tickets, user);
        orderService.getOrderHistory(user).forEach(System.out::println);
    }

}
