package com.dev.cinema.controller;

import com.dev.cinema.model.dto.mappers.MovieMapper;
import com.dev.cinema.model.dto.request.RequestMovieDto;
import com.dev.cinema.model.dto.responce.ResponseMovieDto;
import com.dev.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    public void addMovie(@RequestBody @Valid RequestMovieDto requestMovieDto) {
        movieService.add(movieMapper.mapToMovie(requestMovieDto));
    }

    @GetMapping
    public List<ResponseMovieDto> getAll() {
        return movieService.getAll().stream()
                .map(movieMapper::mapToResponseMovieDto)
                .collect(Collectors.toList());
    }
}
