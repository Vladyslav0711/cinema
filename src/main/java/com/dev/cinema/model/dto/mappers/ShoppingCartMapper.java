package com.dev.cinema.model.dto.mappers;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.dto.request.RequestShoppingCartDto;
import com.dev.cinema.model.dto.responce.ResponseShoppingCartDto;
import com.dev.cinema.model.dto.responce.ResponseTicketDto;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {

    private UserService userService;
    private TicketMapper ticketMapper;

    public ShoppingCartMapper(UserService userService, TicketMapper ticketMapper) {
        this.userService = userService;
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCart mapToShoppingCart(RequestShoppingCartDto requestShoppingCartDto) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(userService.getById(requestShoppingCartDto.getUserId()));
        return shoppingCart;
    }

    public ResponseShoppingCartDto mapToResponseShoppingCartDto(ShoppingCart shoppingCart) {
        ResponseShoppingCartDto responseShoppingCartDto = new ResponseShoppingCartDto();
        responseShoppingCartDto.setCartId(shoppingCart.getId());
        responseShoppingCartDto.setUserId(shoppingCart.getUser().getId());
        List<ResponseTicketDto> tickets = shoppingCart.getTickets().stream()
                .map(ticketMapper::mapToResponseTicketDto)
                .collect(Collectors.toList());
        responseShoppingCartDto.setTickets(tickets);
        return responseShoppingCartDto;
    }
}
