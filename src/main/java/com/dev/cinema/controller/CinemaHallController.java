package com.dev.cinema.controller;

import com.dev.cinema.model.dto.mappers.CinemaHallMapper;
import com.dev.cinema.model.dto.request.RequestCinemaHallDto;
import com.dev.cinema.model.dto.responce.ResponseCinemaHallDto;
import com.dev.cinema.service.CinemaHallService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cinemahalls")
public class CinemaHallController {

    private CinemaHallService cinemaHallService;
    private CinemaHallMapper cinemaHallMapper;

    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallMapper cinemaHallMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @PostMapping
    public void addCinemaHall(@RequestBody RequestCinemaHallDto requestCinemaHallDto) {
        cinemaHallService.add(cinemaHallMapper.mapToCinemaHall(requestCinemaHallDto));
    }

    @GetMapping
    public List<ResponseCinemaHallDto> getCinemaHalls() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHallMapper::mapToResponseCinemaHallDto)
                .collect(Collectors.toList());
    }
}
