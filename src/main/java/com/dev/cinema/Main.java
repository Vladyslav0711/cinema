package com.dev.cinema;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static final Injector INJECTOR = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService)
                INJECTOR.getInstance(MovieService.class);
        movieService.getAll().forEach(System.out::println);
        Movie movie = new Movie();
        movie.setTittle("Fast and furious");
        movieService.add(movie);

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

        AuthenticationService authenticationService = (AuthenticationService)
                INJECTOR.getInstance(AuthenticationService.class);
        authenticationService.register("vlad@gmail.com", "vlad");
        try {
            System.out.println(authenticationService.login("vlad@gmail.com", "vlad"));
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
        authenticationService.register("ola@gmail.com", "ola12");
        try {
            System.out.println(authenticationService.login("ola@gmail.com", "ola12"));
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

}
