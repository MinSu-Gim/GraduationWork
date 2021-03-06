package com.brother.graduationwork.dto;

import com.brother.graduationwork.domain.Menu;
import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AddMenuReturnDTO {

    private String username;
    private int currAmount;
    private Map<String, List<Menu>> userMenus;
    private int currNumOfPeople;

    public void adduserMenu(String username, List<Menu> userMenus) {
        this.userMenus.put(username, userMenus);
    }
}
