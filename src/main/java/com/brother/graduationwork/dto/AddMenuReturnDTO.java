package com.brother.graduationwork.dto;

import com.brother.graduationwork.domain.Menu;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AddMenuReturnDTO {

    private String username;
    List<Menu> menus;
    private int currAmount;
}
