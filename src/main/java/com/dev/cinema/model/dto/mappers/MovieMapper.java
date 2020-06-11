package com.dev.cinema.model.dto.mappers;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.request.RequestMovieDto;
import com.dev.cinema.model.dto.responce.ResponseMovieDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public ResponseMovieDto mapToResponseMovieDto(Movie movie) {
        ResponseMovieDto responseMovieDto = new ResponseMovieDto();
        responseMovieDto.setMovieId(movie.getId());
        responseMovieDto.setTittle(movie.getTittle());
        responseMovieDto.setDescription(movie.getDescription());
        return responseMovieDto;
    }

    public Movie mapToMovie(RequestMovieDto requestMovieDto) {
        Movie movie = new Movie();
        movie.setTittle(requestMovieDto.getTittle());
        movie.setDescription(requestMovieDto.getDescription());
        return movie;
    }
}
