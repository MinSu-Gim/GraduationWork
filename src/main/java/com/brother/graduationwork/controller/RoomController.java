package com.brother.graduationwork.controller;

import com.brother.graduationwork.domain.Room;
import com.brother.graduationwork.dto.*;
import com.brother.graduationwork.service.MenuServiceImpl;
import com.brother.graduationwork.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final MenuServiceImpl menuService;

    @Transactional
    @PostConstruct
    public void init() {
        RoomDTO roomA = new RoomDTO("치킨", "안뇽", "붉은색", 10000, 1000);
        RoomDTO roomB = new RoomDTO("만다린", "친구들", "푸른색",22500, 10);
        RoomDTO roomC = new RoomDTO("족막", "만나서", "그 사이",34000, 10);
        RoomDTO roomD = new RoomDTO("니드 카페인", "반가웡", "3초",40000, 10);
        RoomDTO roomE = new RoomDTO("오오", "만반잘부", "그 짧은 시간~",50000, 10);
        RoomDTO roomF = new RoomDTO("마지막쓰", "만반잘부", "그 짧은 시간~",50000, 10);

        roomService.createRoom(roomA);
        roomService.createRoom(roomB);
        roomService.createRoom(roomC);
        roomService.createRoom(roomD);
        roomService.createRoom(roomE);
        roomService.createRoom(roomF);
    }

    /**
     * 방 생성
     *
     * @param roomDTO
     * @return
     */
    @PostMapping("/room")
    public Long createRoom(@RequestBody RoomDTO roomDTO) {
        Room createdRoom = roomService.createRoom(roomDTO);

        return createdRoom.getId();
    }

    @GetMapping("/room/{limit}")
    public List<LoadRoomsDTO> getAllRoom(@PathVariable("limit") int limit) {
        log.info(String.valueOf(limit));
        List<Room> rooms = roomService.findAllRooms(limit);

        List<LoadRoomsDTO> loadRoomsDTOS = new ArrayList<>();
        rooms.forEach(r -> {
            LoadRoomsDTO loadRoomsDTO = new LoadRoomsDTO(
                    r.getTitle(),
                    r.getCurrNumOfPeople(),
                    r.getMaximumPeople(),
                    r.getCurrentAmount(),
                    r.getMinimumOrderAmount(),
                    r.getGatheringPlace());

            loadRoomsDTOS.add(loadRoomsDTO);
        });

        return loadRoomsDTOS;
    }

    /**
     * 유저가 공구 방에 입장
     *
     * @param joinRoomDTO
     * @return userJoinRoomId
     *
     * return
     * -1: 그런 방이 없음
     * -2: 그런 유저가 없음
     * -3: 방이 가득 참
     * -4: 현재 사용자가 접속한 방이 있음
     */
    @PostMapping("/joinRoom")
    public Object joinRoom(@RequestBody joinRoomDTO joinRoomDTO) {

        String username = joinRoomDTO.getUsername();
        String roomTitle = joinRoomDTO.getRoomTitle();

        return roomService.userJoinRoom(username, roomTitle);
    }

    @PostMapping("/room/exit")
    public void exitRoom(@RequestBody UserOneParamDTO param) {

        String username = param.getParam();
        menuService.deleteUserMenus(username);
        roomService.exitRoom(username);
    }
}
