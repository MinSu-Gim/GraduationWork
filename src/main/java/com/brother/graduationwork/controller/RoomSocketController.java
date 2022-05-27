package com.brother.graduationwork.controller;

import com.brother.graduationwork.domain.Menu;
import com.brother.graduationwork.dto.ResponseMessage;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;


@Slf4j
@Controller
@ToString
public class RoomSocketController {

    @MessageMapping("/selectMenu")
    @SendTo("/room/menu")
    public String selectMenu(@Payload List<Menu> menus) {

        log.info("메뉴 선택");
        menus.forEach(m -> System.out.println("m = " + m.toString()));

        return "ok";
    }
}
