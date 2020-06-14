package com.dev.cinema.model.dto.responce;

import java.util.List;

public class ResponseOrderDto {
    private Long id;
    private List<ResponseTicketDto> tickets;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ResponseTicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<ResponseTicketDto> tickets) {
        this.tickets = tickets;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
