package com.brother.graduationwork.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Setter @Getter
public class RoomDTO {

    private final LocalDate createdDate;

    private final String createdBy;

    private final int minimumOrderAmount;

    private final int currentAmount;

    private final int numOfPeople;

    private final int currNumOfPeople;
}
