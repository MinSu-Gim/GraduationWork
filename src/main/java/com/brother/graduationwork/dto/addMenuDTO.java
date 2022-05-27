package com.brother.graduationwork.dto;

import com.brother.graduationwork.domain.Menu;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class addMenuDTO {

    private Long roomId;
    private String username;
    List<Menu> menus;
}
