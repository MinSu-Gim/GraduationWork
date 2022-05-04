package com.brother.graduationwork.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Store {

    @Id
    @GeneratedValue
    @Column(name = "STORE_ID")
    private Long id;

    private String storeName;

    @OneToMany(mappedBy = "store")
    private List<Menu> menus = new ArrayList<>();

    public void addMenu(Menu menu) {
        menus.add(menu);
        menu.setStore(this);
    }

    public Store(String storeName) {
        this.storeName = storeName;
    }

    public Store() {
    }
}
