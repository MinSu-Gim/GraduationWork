package com.brother.graduationwork.service;

import com.brother.graduationwork.domain.DuplicatedStatus;
import com.brother.graduationwork.domain.LoginStatus;
import com.brother.graduationwork.domain.Menu;
import com.brother.graduationwork.domain.User;

import java.util.List;

public interface UserService {

    Long registerUser(User user);

    User findUserById(Long id);

    User findUserByNickName(String queryName);

    List<User> findAllUser();

    Long updateUser(User updateUser);

    void increaseMoney(int totalMoney, String user_email);

    LoginStatus loginUser(String email, String pw);

    User findUserByEmail(String email);

    List<Menu> getUserMenus(String username);

    DuplicatedStatus checkDuplicatedEmail(String email);

    DuplicatedStatus checkDuplicatedNickname(String nickname);
}
