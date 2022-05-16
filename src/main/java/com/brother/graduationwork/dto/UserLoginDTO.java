package com.brother.graduationwork.dto;

import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
public class UserLoginDTO {

    private final String user_email;
    private final String user_pw;
}
