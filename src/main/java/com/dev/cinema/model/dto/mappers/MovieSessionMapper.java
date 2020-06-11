package com.dev.cinema.model.dto.mappers;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.request.RequestMovieSessionDto;
import com.dev.cinema.model.dto.responce.ResponseMovieSessionDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {

    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapper(MovieService movieService,
                              CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSession mapToMovieSession(RequestMovieSessionDto
                                                  requestMovieSessionDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService
                .getById(requestMovieSessionDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService
                .getById(requestMovieSessionDto.getCinemaHallId()));
        movieSession.setShowTime(requestMovieSessionDto.getShowTime());
        return movieSession;
    }

    public ResponseMovieSessionDto mapToResponseMovieSessionDto(
            MovieSession movieSession) {
        ResponseMovieSessionDto responseMovieSessionDto = new ResponseMovieSessionDto();
        responseMovieSessionDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        responseMovieSessionDto.setMovieId(movieSession.getMovie().getId());
        responseMovieSessionDto.setMovieSessionId(movieSession.getId());
        responseMovieSessionDto.setMovieTittle(movieSession.getMovie().getTittle());
        responseMovieSessionDto.setShowTime(movieSession.getShowTime());
        return responseMovieSessionDto;
    }
}
