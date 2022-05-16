package com.brother.graduationwork.controller;

import com.brother.graduationwork.domain.Room;
import com.brother.graduationwork.domain.User;
import com.brother.graduationwork.dto.RoomDTO;
import com.brother.graduationwork.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomServiceImpl;

    @Transactional
    @PostConstruct
    public void init() {
        RoomDTO roomA = new RoomDTO("치킨", "안뇽", "붉은색", 10000, 2);
        RoomDTO roomB = new RoomDTO("만다린", "친구들", "푸른색",22500, 3);
        RoomDTO roomC = new RoomDTO("족막", "만나서", "그 사이",34000, 4);
        RoomDTO roomD = new RoomDTO("니드 카페인", "반가웡", "3초",40000, 5);
        RoomDTO roomE = new RoomDTO("오오", "만반잘부", "그 짧은 시간~",50000, 6);
        RoomDTO roomF = new RoomDTO("마지막쓰", "만반잘부", "그 짧은 시간~",50000, 6);

        roomServiceImpl.createRoom(roomA);
        roomServiceImpl.createRoom(roomB);
        roomServiceImpl.createRoom(roomC);
        roomServiceImpl.createRoom(roomD);
        roomServiceImpl.createRoom(roomE);

        roomServiceImpl.createRoom(roomA);
        roomServiceImpl.createRoom(roomB);
        roomServiceImpl.createRoom(roomC);
        roomServiceImpl.createRoom(roomD);
        roomServiceImpl.createRoom(roomE);

        roomServiceImpl.createRoom(roomA);
        roomServiceImpl.createRoom(roomB);
        roomServiceImpl.createRoom(roomC);
        roomServiceImpl.createRoom(roomD);
        roomServiceImpl.createRoom(roomE);

        roomServiceImpl.createRoom(roomA);
        roomServiceImpl.createRoom(roomB);
        roomServiceImpl.createRoom(roomC);
        roomServiceImpl.createRoom(roomD);
        roomServiceImpl.createRoom(roomE);

        roomServiceImpl.createRoom(roomC);
        roomServiceImpl.createRoom(roomD);
        roomServiceImpl.createRoom(roomF);
    }

    @PostMapping("/room")
    public Long createRoom(@RequestBody RoomDTO roomDTO) {
        Room createdRoom = roomServiceImpl.createRoom(roomDTO);

        return createdRoom.getId();
    }

    @GetMapping("/room/{limit}")
    public List<Room> getAllRoom(@PathVariable("limit") int limit) {
        log.info(String.valueOf(limit));
        List<Room> rooms = roomServiceImpl.findAllRooms(limit);
        return rooms;
    }
}
