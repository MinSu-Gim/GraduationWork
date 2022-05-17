package com.brother.graduationwork.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class KakaoPayDTO {

    private String money;
    private String user_email;

    public KakaoPayDTO() {
    }

    public KakaoPayDTO(String money, String user_email) {
        this.money = money;
        this.user_email = user_email;
    }
}
