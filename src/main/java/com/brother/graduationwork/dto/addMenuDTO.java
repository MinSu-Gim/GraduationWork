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

    private String username;
//    private String menuName;
//    private int price;
//    private int quantity;
    List<Menu> menus;
}
