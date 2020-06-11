package com.dev.cinema.model.dto.responce;

import java.util.List;

public class ResponseShoppingCartDto {
    private Long cartId;
    private Long userId;
    private List<ResponseTicketDto> tickets;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ResponseTicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<ResponseTicketDto> tickets) {
        this.tickets = tickets;
    }
}
