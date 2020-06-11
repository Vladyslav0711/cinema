package com.dev.cinema.controller;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.mappers.MovieMapper;
import com.dev.cinema.model.dto.mappers.MovieSessionMapper;
import com.dev.cinema.model.dto.request.RequestMovieSessionDto;
import com.dev.cinema.model.dto.responce.ResponseMovieSessionDto;
import com.dev.cinema.service.MovieSessionService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("moviesessions")
public class MovieSessionController {

    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @PostMapping
    public void addMovieSession(@RequestBody RequestMovieSessionDto
                                        requestMovieSessionDto) {
        movieSessionService.add(movieSessionMapper
                .mapToMovieSession(requestMovieSessionDto));
    }

    @GetMapping("/available")
    public List<ResponseMovieSessionDto> getAvailableSessions(@RequestParam Long movieId,
                                                              @RequestParam LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
        .map(movieSessionMapper::mapToResponseMovieSessionDto).collect(Collectors.toList());
    }
}
