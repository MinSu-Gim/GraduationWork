package com.brother.graduationwork.dto;

import com.brother.graduationwork.domain.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@Builder
public class ExitRoomReturnDTO {

    private int currNumOfPeople;
    private int currAmount;
    private Map<String, List<Menu>> userMenus;

    public void adduserMenu(String username, List<Menu> userMenus) {
        this.userMenus.put(username, userMenus);
    }
}
