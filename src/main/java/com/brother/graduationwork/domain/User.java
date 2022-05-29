package com.brother.graduationwork.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "room")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    private boolean joinRoom;

    @ElementCollection
    @CollectionTable(
            name = "MENUS",
            joinColumns = @JoinColumn(name = "USER_ID")
    )
    private List<Menu> menus;

    private String user_email;

    private String user_pw;

    private String user_nickname;

    private String user_phoneNumber;

    private int money;

    public int getTotalPriceOfMenus() {
        int totalPrice = 0;
        for (Menu menu : this.menus) {
            totalPrice += menu.getPrice() * menu.getQuantity();
        }

        return totalPrice;
    }
    public User(String user_email, String user_pw, String nickname, String phoneNumber) {
        this.user_email = user_email;
        this.user_pw = user_pw;
        this.user_nickname = nickname;
        this.user_phoneNumber = phoneNumber;
        this.money = 0;
        this.joinRoom = false;
    }

    public User() {

    }

    public boolean isJoinRoom() {
        return joinRoom;
    }
}
