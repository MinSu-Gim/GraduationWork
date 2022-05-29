package com.brother.graduationwork.controller;

import com.brother.graduationwork.domain.Menu;
import com.brother.graduationwork.domain.Room;
import com.brother.graduationwork.domain.Status;
import com.brother.graduationwork.domain.User;
import com.brother.graduationwork.dto.addMenuDTO;
import com.brother.graduationwork.dto.AddMenuReturnDTO;
import com.brother.graduationwork.service.MenuServiceImpl;
import com.brother.graduationwork.service.RoomService;
import com.brother.graduationwork.service.UserService;
import com.brother.graduationwork.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class MenuController {

    private final RoomService roomService;
    private final MenuServiceImpl menuService;
    private final WebSocketService webSocketService;
    private final UserService userService;

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

        User findUser = userService.findUserByNickName(username);
        int beforePrice = findUser.getTotalPriceOfMenus();

        menuService.deleteUserMenus(username);
        status = menuService.addMenusToUser(username, menus);
        if (status.equals(Status.Fail))
            return status;
        else {

            int after = menuService.getTotalMenusPrice(menus);
            int currAmount = roomService.changeCurrAmount(roomId, beforePrice, after);
            int roomCurrNumOfPeople = roomService.getRoomCurrNumOfPeople(roomId);
            AddMenuReturnDTO addMenuReturnDTO = AddMenuReturnDTO.builder()
                    .username(username)
                    .userMenus(new HashMap<>())
                    .currAmount(currAmount)
                    .currNumOfPeople(roomCurrNumOfPeople)
                    .build();

            Room roomById = roomService.findRoomById(roomId);
            List<User> users = roomById.getUsers();
            for (User user : users) {
                String user_nickname = user.getUser_nickname();
                List<Menu> userMenus = userService.getUserMenus(user_nickname);

                addMenuReturnDTO.adduserMenu(user_nickname, userMenus);
            }

            webSocketService.notifyOtherUserMenus(roomId, addMenuReturnDTO);
        }

        return status;
    }
}
