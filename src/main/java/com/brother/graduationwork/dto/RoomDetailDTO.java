package com.brother.graduationwork.dto;

import com.brother.graduationwork.domain.Menu;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;


@Getter
@Builder
@ToString
public class RoomDetailDTO {

    private Long roomId;
    private String createdBy;
    private String title;
    private int currNumOfPeople;
    private int maximumPeople;
    private int currAmount;
    private int minimumOrderAmount;
    private String gatheringPlace;
    private Map<String, List<Menu>> userMenus;

    public void adduserMenu(String username, List<Menu> userMenus) {
        this.userMenus.put(username, userMenus);
        System.out.println("들어갔는지 확인 = " + this.userMenus.get(username));
    }
}
