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

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    @PersistenceContext
    EntityManager em;

    private final UserService userServiceImpl;

    @Override
    public void createRoom(String userNickname, RoomDTO roomDTO) {

    }
}
