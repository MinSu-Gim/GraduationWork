package com.brother.graduationwork.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Menu {

    private String menuName;
    private int price;
    private int quantity;
}
