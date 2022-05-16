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
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    @PersistenceContext
    EntityManager em;

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
    public List<Room> findAllRooms(int offset, int limit) {
        return em.createQuery("select r from Room r", Room.class)
                .getResultList();
//                .setFirstResult(offset)
//                .setMaxResults(limit)

    }
}
