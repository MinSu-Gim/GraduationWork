package com.brother.graduationwork.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Setter
@Getter
public class RoomDTO {

    private final String title;

    private final String createdBy;

    private final String gatheringPlace;

    private final int minimumOrderAmount;

    private final int numOfPeople;
}
