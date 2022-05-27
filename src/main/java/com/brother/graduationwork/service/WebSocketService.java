package com.brother.graduationwork.service;

import com.brother.graduationwork.dto.Message;
import com.brother.graduationwork.dto.addMenuDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public void notifyOtherUserMenus(addMenuDTO addMenuDTO) {
        messagingTemplate.convertAndSend("/room/menu", addMenuDTO);
    }

    public void notifyOtherMessage(Message message) {
        messagingTemplate.convertAndSend("/chat/receive", message);
    }
}
