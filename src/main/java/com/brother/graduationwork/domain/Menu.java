package com.brother.graduationwork.domain;

import lombok.*;

import javax.persistence.Embeddable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Embeddable
public class Menu {

    private String menuName;
    private int price;
    private int quantity;
}
