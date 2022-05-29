package com.brother.graduationwork.controller;

import com.brother.graduationwork.domain.Menu;
import com.brother.graduationwork.domain.Room;
import com.brother.graduationwork.domain.Status;
import com.brother.graduationwork.dto.addMenuDTO;
import com.brother.graduationwork.dto.AddMenuReturnDTO;
import com.brother.graduationwork.service.MenuServiceImpl;
import com.brother.graduationwork.service.RoomService;
import com.brother.graduationwork.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class MenuController {

    private final RoomService roomService;
    private final MenuServiceImpl menuService;
    private final WebSocketService webSocketService;

    @PostMapping("/menu")
    public Status addMenus(@RequestBody addMenuDTO addMenuDTO) {

        String username = addMenuDTO.getUsername();
        List<Menu> menus = addMenuDTO.getMenus();

        Long roomId = roomService.getUserRoomId(username);
        if (roomId == -1)
            return Status.Fail;

        Status status = roomService.checkRoomExistsById(roomId);
        if (status.equals(Status.Fail))
            return status;

        menuService.deleteUserMenus(username);
        status = menuService.addMenusToUser(username, menus);
        if (status.equals(Status.Fail))
            return status;
        else {

            int totalMenusPrice = menuService.getTotalMenusPrice(menus);
            roomService.changeCurrAmount(roomId, totalMenusPrice);

            AddMenuReturnDTO addMenuReturnDTO = AddMenuReturnDTO.builder()
                    .username(username)
                    .menus(menus)
                    .currAmount(totalMenusPrice)
                    .build();

            webSocketService.notifyOtherUserMenus(roomId, addMenuReturnDTO);
        }

        return status;
    }
}
