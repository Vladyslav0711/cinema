package com.dev.cinema.model.dto.mappers;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.responce.ResponseTicketDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public ResponseTicketDto mapToResponseTicketDto(Ticket ticket) {
        ResponseTicketDto responseTicketDto = new ResponseTicketDto();
        responseTicketDto.setMovieSessionId(ticket.getMovieSession().getId());
        responseTicketDto.setTicketId(ticket.getId());
        return responseTicketDto;
    }
}
