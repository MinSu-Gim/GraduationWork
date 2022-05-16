package com.brother.graduationwork.service;

import com.brother.graduationwork.domain.DuplicatedStatus;
import com.brother.graduationwork.domain.LoginStatus;
import com.brother.graduationwork.domain.User;

import java.util.List;

public interface UserService {

    Long registerUser(User user);

    User findUserById(Long id);

    List<User> findUserByNickName(String queryName);

    List<User> findAllUser();

    Long updateUser(User updateUser);

    void increaseMoney(int totalMoney, Long userId);

    LoginStatus loginUser(String email, String pw);

    User findUserByEmail(String email);

    DuplicatedStatus checkDuplicatedEmail(String email);

    DuplicatedStatus checkDuplicatedNickname(String nickname);
}
