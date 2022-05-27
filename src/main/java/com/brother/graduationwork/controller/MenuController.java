package com.brother.graduationwork.controller;

import com.brother.graduationwork.domain.Menu;
import com.brother.graduationwork.domain.Status;
import com.brother.graduationwork.domain.User;
import com.brother.graduationwork.dto.addMenuDTO;
import com.brother.graduationwork.service.MenuServiceImpl;
import com.brother.graduationwork.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.isNull;


@RestController
@RequiredArgsConstructor
@Slf4j
public class MenuController {

    private final MenuServiceImpl menuService;

    @PostMapping("/menu")
    public Status addMenus(@RequestBody addMenuDTO addMenuDTO) {

        String username = addMenuDTO.getUsername();
        List<Menu> menus = addMenuDTO.getMenus();

        Status status = menuService.addMenusToUser(username, menus);

        return status;
    }
}
