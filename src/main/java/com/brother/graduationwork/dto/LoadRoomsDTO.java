package com.brother.graduationwork.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoadRoomsDTO {

    private String title;
    private int currNumOfPeople;
    private int maximumPeople;
    private int currAmount;
    private int minimumOrderAmount;
    private String gatheringPlace;
}
