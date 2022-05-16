package com.brother.graduationwork.service;

import com.brother.graduationwork.domain.Room;
import com.brother.graduationwork.domain.User;
import com.brother.graduationwork.dto.RoomDTO;

import java.util.List;

public interface RoomService {

    Room createRoom(RoomDTO roomDTO);

    List<Room> findAllRooms(int offset, int limit);
}
