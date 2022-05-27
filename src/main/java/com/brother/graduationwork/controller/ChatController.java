package com.brother.graduationwork.controller;

import com.brother.graduationwork.domain.Menu;
import com.brother.graduationwork.dto.Message;
import com.brother.graduationwork.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final WebSocketService webSocketService;

    @MessageMapping("/send")
    public void handleChatting(@Payload Message message) {

        webSocketService.notifyOtherMessage(message);
    }
}
