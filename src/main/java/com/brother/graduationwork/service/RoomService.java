package com.brother.graduationwork.service;

import com.brother.graduationwork.domain.Room;
import com.brother.graduationwork.domain.Status;
import com.brother.graduationwork.domain.User;
import com.brother.graduationwork.dto.RoomDTO;
import com.brother.graduationwork.dto.RoomDetailDTO;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

public interface RoomService {

    Room createRoom(RoomDTO roomDTO);

    List<Room> findAllRooms(int limit);

    Object userJoinRoom(String username, String roomTitle);

    void exitRoom(String username, int price);

    Optional<Room> findRoomByTitle(String roomTitle);

    Status checkRoomExistsById(Long id);

    Long getUserRoomId(String username);

    int changeCurrAmount(Long roomId, int before, int after);

    int getRoomCurrNumOfPeople(Long roomId);

    RoomDetailDTO getRoomDetailInfo(Long roomId);

    Room findRoomById(Long roomId);
}
