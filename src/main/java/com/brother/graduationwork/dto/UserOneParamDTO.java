package com.brother.graduationwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserOneParamDTO {

    private String param;

    public UserOneParamDTO(String param) {
        this.param = param;
    }

    public UserOneParamDTO() {
    }
}
