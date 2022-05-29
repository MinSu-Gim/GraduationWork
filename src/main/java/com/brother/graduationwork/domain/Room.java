package com.brother.graduationwork.domain;

import lombok.Builder;
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

    private String title;

    private String gatheringPlace;

    private LocalDate createdDate;

    private String createdBy;

    private int minimumOrderAmount;

    private int currentAmount;

    private int maximumPeople;

    private int currNumOfPeople;

    public void addPerson(User user) {
        users.add(user);
        user.setRoom(this);
        currNumOfPeople += 1;
    }

    public void deletePerson(User user) {
        users.remove(user);
        user.setRoom(null);
        currNumOfPeople -= 1;

        for (User user1 : users) {
            System.out.println("user.getUser_nickname() = " + user1.getUser_nickname());
        }
    }

    @Builder
    public Room(String title, String gatheringPlace, String createdBy, int minimumOrderAmount, int numOfPeople) {
        this.title = title;
        this.gatheringPlace = gatheringPlace;
        this.createdDate = LocalDate.now();
        this.createdBy = createdBy;
        this.minimumOrderAmount = minimumOrderAmount;
        this.currentAmount = 0;
        this.maximumPeople = numOfPeople;
        this.currNumOfPeople = 1;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", gatheringPlace='" + gatheringPlace + '\'' +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                ", minimumOrderAmount=" + minimumOrderAmount +
                ", currentAmount=" + currentAmount +
                ", maximumPeople=" + maximumPeople +
                ", currNumOfPeople=" + currNumOfPeople +
                '}';
    }
}
