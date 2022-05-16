package com.brother.graduationwork.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class KakaoPayDTO {

    private final String money;
    private final Long id;
}
