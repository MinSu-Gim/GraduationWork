package com.brother.graduationwork.service;

import com.brother.graduationwork.domain.Menu;
import com.brother.graduationwork.domain.Status;
import com.brother.graduationwork.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.util.Objects.isNull;


@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class MenuServiceImpl {

    @PersistenceContext
    EntityManager em;

    private final UserService userService;

    public Status addMenusToUser(String username, List<Menu> menus) {

        Status status = Status.Success;

        User findUser = userService.findUserByNickName(username);
        if (isNull(findUser)) {
            status = Status.Fail;
        } else {
            findUser.getMenus().addAll(menus);
        }

        return status;
    }

    public int deleteUserMenus(String username) {

        int totalMenusPrice = 0;

        User findUser = userService.findUserByNickName(username);
        if (isNull(findUser)) {
            log.warn("찾는 유저 X in deleteUserMenus");
        } else {
            List<Menu> findUserMenus = findUser.getMenus();

            totalMenusPrice = getTotalMenusPrice(findUserMenus);
            findUserMenus.clear();
        }

        return totalMenusPrice;
    }

    public int getTotalMenusPrice(List<Menu> menus) {

        int totalPrice = 0;
        for (Menu menu : menus) {
            totalPrice += menu.getPrice() * menu.getQuantity();
        }

        return totalPrice;
    }
}
