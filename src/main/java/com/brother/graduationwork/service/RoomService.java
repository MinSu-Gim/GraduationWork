package com.brother.graduationwork.service;

import com.brother.graduationwork.domain.Room;
import com.brother.graduationwork.domain.User;
import com.brother.graduationwork.dto.RoomDTO;

public interface RoomService {

    void createRoom(String userNickname, RoomDTO roomDTO);
}
