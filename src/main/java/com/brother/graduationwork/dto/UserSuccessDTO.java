package com.brother.graduationwork.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserSuccessDTO {

    private String user_email;

    public UserSuccessDTO(String user_email) {
        this.user_email = user_email;
    }

    public UserSuccessDTO() {
    }
}
