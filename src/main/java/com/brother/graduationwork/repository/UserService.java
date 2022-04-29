package com.brother.graduationwork.repository;

import com.brother.graduationwork.domain.User;

import java.util.List;

public interface UserService {

    Long registerUser(User user);

    User findUserById(Long id);

    List<User> findUserByName(String queryName);

    List<User> findAllUser();

    Long updateUser(User updateUser);
}
