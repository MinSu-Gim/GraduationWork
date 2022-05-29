package com.brother.graduationwork.service;

import com.brother.graduationwork.domain.Room;
import com.brother.graduationwork.domain.Status;
import com.brother.graduationwork.domain.User;
import com.brother.graduationwork.dto.RoomDTO;
import com.brother.graduationwork.dto.RoomDetailDTO;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    Room createRoom(RoomDTO roomDTO);

    List<Room> findAllRooms(int limit);

    boolean checkIfUserConnectAnyRoom(String username);

    RoomDetailDTO userJoinRoom(String username, String roomTitle);

    Optional<Room> findRoomByTitle(String roomTitle);

    Status checkRoomExistsById(Long id);
}
