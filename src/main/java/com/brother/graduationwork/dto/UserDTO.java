package com.brother.graduationwork.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    private final String user_email;

    private final String user_pw;

    private final String user_nickname;

    private final String user_phoneNumber;

}
