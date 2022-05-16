package com.brother.graduationwork.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginReturnDTO {

    private final String nickname;
    private final int cash;

    public UserLoginReturnDTO(String nickname, int cash) {
        this.nickname = nickname;
        this.cash = cash;
    }
}
