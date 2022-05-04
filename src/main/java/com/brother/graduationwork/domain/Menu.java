package com.brother.graduationwork.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Menu {

    @Id
    @GeneratedValue
    @Column(name = "MENU_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    private String foodName;

    private int price;

    public Menu(String foodName, int price) {
        this.foodName = foodName;
        this.price = price;
    }

    public Menu() {
    }
}
