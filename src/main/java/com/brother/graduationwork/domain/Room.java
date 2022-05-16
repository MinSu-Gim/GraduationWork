package com.brother.graduationwork.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue
    @Column(name = "ROOM_ID")
    private Long id;

    @OneToMany(mappedBy = "room")
    private List<User> users = new ArrayList<>();

    private LocalDate createdDate;

    private String createdBy;

    private int minimumOrderAmount;

    private int currentAmount;

    private int numOfPeople;

    private int currNumOfPeople;

    public void addPerson(User user) {
        users.add(user);
        user.setRoom(this);
    }

    public Room(String createdBy, int minimumOrderAmount, int numOfPeople) {
        this.createdDate = LocalDate.now();
        this.createdBy = createdBy;
        this.minimumOrderAmount = minimumOrderAmount;
        this.currentAmount = 0;
        this.numOfPeople = numOfPeople;
        this.currNumOfPeople = 0;
    }
}
