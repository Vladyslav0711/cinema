package com.dev.cinema.model.dto.mappers;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.dto.request.RequestCinemaHallDto;
import com.dev.cinema.model.dto.responce.ResponseCinemaHallDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {
    public CinemaHall mapToCinemaHall(RequestCinemaHallDto requestCinemaHallDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(requestCinemaHallDto.getCapacity());
        cinemaHall.setDescription(requestCinemaHallDto.getDescription());
        return cinemaHall;
    }

    public ResponseCinemaHallDto mapToResponseCinemaHallDto(CinemaHall cinemaHall) {
        ResponseCinemaHallDto responseCinemaHallDto = new ResponseCinemaHallDto();
        responseCinemaHallDto.setCapacity(cinemaHall.getCapacity());
        responseCinemaHallDto.setDescription(cinemaHall.getDescription());
        responseCinemaHallDto.setId(cinemaHall.getId());
        return responseCinemaHallDto;
    }
}
