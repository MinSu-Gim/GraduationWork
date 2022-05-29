package com.brother.graduationwork.service;

import com.brother.graduationwork.dto.ExitRoomReturnDTO;
import com.brother.graduationwork.dto.Message;
import com.brother.graduationwork.dto.AddMenuReturnDTO;
import com.brother.graduationwork.dto.RoomDetailDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public void notifyOtherUserMenus(Long roomId, AddMenuReturnDTO addMenuReturnDTO) {
        messagingTemplate.convertAndSend("/room/" + roomId, addMenuReturnDTO);
    }

    public void notifyLeftUser(Long roomId, ExitRoomReturnDTO exitRoomReturnDTO) {
        messagingTemplate.convertAndSend("/room/" + roomId, exitRoomReturnDTO);
    }

    public void notifyOtherMessage(Long roomId, Message message) {
        messagingTemplate.convertAndSend("/chat/receive/" + roomId, message);
    }
}
