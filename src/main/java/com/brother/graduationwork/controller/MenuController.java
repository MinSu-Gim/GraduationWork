package com.brother.graduationwork.controller;

import com.brother.graduationwork.domain.Menu;
import com.brother.graduationwork.domain.Status;
import com.brother.graduationwork.dto.addMenuDTO;
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

        Long roomId = addMenuDTO.getRoomId();

        Status status = roomService.checkRoomExistsById(roomId);
        if (status.equals(Status.Fail))
            return status;

        String username = addMenuDTO.getUsername();
        List<Menu> menus = addMenuDTO.getMenus();

        status = menuService.addMenusToUser(username, menus);
        if (status.equals(Status.Fail))
            return status;
        else {
            webSocketService.notifyOtherUserMenus(addMenuDTO);
        }

        return status;
    }
}
