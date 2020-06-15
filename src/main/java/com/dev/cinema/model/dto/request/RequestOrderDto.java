package com.dev.cinema.model.dto.request;

import javax.validation.constraints.NotNull;

public class RequestOrderDto {
    @NotNull
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
