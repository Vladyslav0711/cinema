package com.dev.cinema.model.dto.mappers;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.responce.ResponseUserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public ResponseUserDto mapToResponseUserDto(User user) {
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setId(user.getId());
        responseUserDto.setEmail(user.getEmail());
        return responseUserDto;
    }
}
