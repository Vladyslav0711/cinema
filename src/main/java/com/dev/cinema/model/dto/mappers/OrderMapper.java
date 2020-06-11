package com.dev.cinema.model.dto.mappers;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.request.RequestOrderDto;
import com.dev.cinema.model.dto.responce.ResponseOrderDto;
import com.dev.cinema.model.dto.responce.ResponseTicketDto;
import com.dev.cinema.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private UserService userService;
    private TicketMapper ticketMapper;

    public OrderMapper(UserService userService, TicketMapper ticketMapper) {
        this.userService = userService;
        this.ticketMapper = ticketMapper;
    }

    public Order mapToOrder(RequestOrderDto requestOrderDto) {
        Order order = new Order();
        order.setUser(userService.getById(requestOrderDto.getUserId()));
        return order;
    }

    public ResponseOrderDto mapToResponseOrderDto(Order order) {
        ResponseOrderDto responseOrderDto = new ResponseOrderDto();
        responseOrderDto.setId(order.getId());
        List<ResponseTicketDto> tickets = order.getTickets().stream()
                .map(ticketMapper::mapToResponseTicketDto)
                .collect(Collectors.toList());
        responseOrderDto.setTickets(tickets);
        return responseOrderDto;
    }
}
