package com.brother.graduationwork.service;

import com.brother.graduationwork.domain.Room;
import com.brother.graduationwork.domain.User;
import com.brother.graduationwork.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
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
    public Long userJoinRoom(String username, String roomTitle) {

        Optional<Room> findRoom = findRoomByTitle(roomTitle);
        User findUser = userService.findUserByNickName(username);

        if (findRoom.isEmpty() || isNull(findUser))
            return 0L;

        Room room = findRoom.get();
        if (room.getCurrNumOfPeople() == room.getMaximumPeople())
            return -1L;

        room.addPerson(findUser);
        findUser.setRoom(room);

        System.out.println("room.getUsers() = " + room.getUsers());

        return room.getId();
    }

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
}
