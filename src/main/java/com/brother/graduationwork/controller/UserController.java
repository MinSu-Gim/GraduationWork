package com.brother.graduationwork.controller;

import com.brother.graduationwork.domain.User;
import com.brother.graduationwork.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userServiceImpl;

    /**
     * 서버에 올릴 때, 코드 제거하기
     */
    @Transactional
    @PostConstruct
    public void init() {
        User userA = new User("aa@gmail.com", "pw1", "김민수", "01012345678");
        User userB = new User("bb@gmail.com", "pw2", "이종렬", "01023456789");
        User userC = new User("cc@gmail.com", "pw3", "나재현", "01034567890");
        User userD = new User("dd@gmail.com", "pw4", "김민수", "01026747890");

        userServiceImpl.registerUser(userA);
        userServiceImpl.registerUser(userB);
        userServiceImpl.registerUser(userC);
        userServiceImpl.registerUser(userD);
    }

    /**
     * 아이디로 User 조회
     *
     * @param id
     * @return user where user's id == id
     */
    @GetMapping("/user/{id:[0-9]*}")
    public User getUserById(@PathVariable("id") Long id) {

        log.info("UserController.getUser");

        User findUser = userServiceImpl.findUserById(id);
        return findUser;
    }

    /**
     * 닉네임으로 User 조회
     *
     * @param nickname
     * @return user where user's nickname == nickname
     */
    @GetMapping("/user/{nickname:[ㄱ-힣]*}")
    public List<User> getUserByName(@PathVariable("nickname") String nickname) {

        log.info("UserController.getUser");

        List<User> users = userServiceImpl.findUserByNickName(nickname);
        return users;
    }

    /**
     * 모든 User 조회
     *
     * @return all Users
     */
    @GetMapping("/user/all")
    public List<User> getUserAll() {

        log.info("UserController.getUserAll");

        List<User> users = userServiceImpl.findAllUser();
        return users;
    }

    /**
     * User 회원 가입
     *
     * @param user
     * @return user.id
     */
    @PostMapping("/user")
    public Long addUser(@RequestBody User user) {

        log.info("UserController.addUser");

        Long userId = userServiceImpl.registerUser(user);
        return userId;
    }

    /**
     * User 정보 갱신
     *
     * @param user
     * @return updateUserId
     */
    @PutMapping("/user")
    public Long test(@RequestBody User user) {

        log.info("UserController.test");

        Long updateUserId = userServiceImpl.updateUser(user);
        return updateUserId;
    }
}
