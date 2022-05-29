package com.brother.graduationwork.service;

import com.brother.graduationwork.domain.Menu;
import com.brother.graduationwork.domain.Room;
import com.brother.graduationwork.domain.Status;
import com.brother.graduationwork.domain.User;
import com.brother.graduationwork.dto.RoomDTO;
import com.brother.graduationwork.dto.RoomDetailDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    @PersistenceContext
    EntityManager em;
    
    private final UserService userService;
    
    @Override
    public Room createRoom(RoomDTO roomDTO) {
        Room room = Room.builder()
                .title(roomDTO.getTitle())
                .gatheringPlace(roomDTO.getGatheringPlace())
                .createdBy(roomDTO.getCreatedBy())
                .minimumOrderAmount(roomDTO.getMinimumOrderAmount())
                .numOfPeople(roomDTO.getNumOfPeople())
                .build();

        em.persist(room);

        return room;
    }

    @Override
    public List<Room> findAllRooms(int limit) {
        return em.createQuery("select r from Room r", Room.class)
                .setFirstResult(0)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public Object userJoinRoom(String username, String roomTitle) {

        Optional<Room> findRoom = findRoomByTitle(roomTitle);
        User findUser = userService.findUserByNickName(username);

        if (findRoom.isEmpty()) {
            log.error("방이 존재하지 않음");
            return -1;
        }
            
        if (isNull(findUser)) {
            log.error("유저가 존재하지 않음");
            return -2;
        }
        
        Room room = findRoom.get();
        if (room.getCurrNumOfPeople() == room.getMaximumPeople()) {
            log.error("방이 가득찼음");
            return -3;
        }

        boolean isUserJoinRoom = findUser.isJoinRoom();
        if (isUserJoinRoom) {
            return -4;
        } else {
            findUser.setJoinRoom(true);
        }

        // 방에 사람 정보 넣기
        room.addPerson(findUser);

        RoomDetailDTO roomDetailInfo = getRoomDetailInfo(room.getId());

        return roomDetailInfo;
    }

    public void exitRoom(String username) {
        User findUser = userService.findUserByNickName(username);
        Room room = findUser.getRoom();
        if (!isNull(room))
            room.deletePerson(findUser);
    }

    @Override
    public Optional<Room> findRoomByTitle(String roomTitle) {

        Optional<Room> room = Optional.empty();

        try {
            Room findRoom = em.createQuery("select r from Room r where r.title = :title", Room.class)
                    .setParameter("title", roomTitle)
                    .getSingleResult();
            room = Optional.ofNullable(findRoom);
        } catch (NoResultException e) {
            log.info("그런 방이 없습니다.");
        }

        return room;
    }

    @Override
    public Long getUserRoomId(String username) {

        User findUser = userService.findUserByNickName(username);
        Room RoomOfFindUser = findUser.getRoom();
        if (isNull(RoomOfFindUser))
            return -1L;

        return RoomOfFindUser.getId();
    }

    @Override
    public Status checkRoomExistsById(Long id) {

        Status status = Status.Success;

        Room findRoom = em.find(Room.class, id);
        if (isNull(findRoom)) {
            status = Status.Fail;
            log.warn("그런 방이 없습니다.");
        }

        return status;
    }

    @Override
    public RoomDetailDTO getRoomDetailInfo(Long roomId) {

        Room findRoom = em.find(Room.class, roomId);
        if (isNull(findRoom)) {
            return null;
        }

        RoomDetailDTO roomDetailDTO = RoomDetailDTO.builder()
                .roomId(findRoom.getId())
                .createdBy(findRoom.getCreatedBy())
                .title(findRoom.getTitle())
                .currNumOfPeople(findRoom.getCurrNumOfPeople())
                .maximumPeople(findRoom.getMaximumPeople())
                .currAmount(findRoom.getCurrentAmount())
                .minimumOrderAmount(findRoom.getMinimumOrderAmount())
                .gatheringPlace(findRoom.getGatheringPlace())
                .userMenus(new HashMap<>())
                .build();

        List<User> users = findRoom.getUsers();
        log.info("방 안의 User 수: " + users.size());
        log.info("방 안의 User 정보");
        for (User user : users) {
            String user_nickname = user.getUser_nickname();
            log.info("이름: " + user_nickname);

            List<Menu> userMenus = userService.getUserMenus(user_nickname);
            log.info("메뉴: " + userMenus);

            roomDetailDTO.adduserMenu(user_nickname, userMenus);
        }

        return roomDetailDTO;
    }
}
