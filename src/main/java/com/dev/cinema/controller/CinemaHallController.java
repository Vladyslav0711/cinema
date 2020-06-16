package com.dev.cinema.controller;

import com.dev.cinema.model.dto.mappers.CinemaHallMapper;
import com.dev.cinema.model.dto.request.RequestCinemaHallDto;
import com.dev.cinema.model.dto.responce.ResponseCinemaHallDto;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {

    private CinemaHallService cinemaHallService;
    private CinemaHallMapper cinemaHallMapper;

    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallMapper cinemaHallMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @PostMapping
    public void addCinemaHall(@RequestBody @Valid RequestCinemaHallDto requestCinemaHallDto) {
        cinemaHallService.add(cinemaHallMapper.mapToCinemaHall(requestCinemaHallDto));
    }

    @GetMapping
    public List<ResponseCinemaHallDto> getCinemaHalls() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHallMapper::mapToResponseCinemaHallDto)
                .collect(Collectors.toList());
    }
}
