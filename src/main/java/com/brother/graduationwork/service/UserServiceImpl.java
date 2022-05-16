package com.brother.graduationwork.service;

import com.brother.graduationwork.domain.DuplicatedStatus;
import com.brother.graduationwork.domain.LoginStatus;
import com.brother.graduationwork.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @PersistenceContext
    EntityManager em;

    @Override
    public LoginStatus loginUser(String email, String pw) {

        LoginStatus loginStatus;

        try {
            User findUser = em.createQuery("select m from User m where m.user_email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();

            if (pw.equals(findUser.getUser_pw())) {
                loginStatus = LoginStatus.Success;
            } else {
                loginStatus = LoginStatus.WrongPassword;
            }

        } catch (NoResultException e) {
            loginStatus = LoginStatus.InvalidEmail;
        }

        return loginStatus;
    }

    @Override
    public Long registerUser(User user) {

        em.persist(user);

        return user.getId();
    }

    @Override
    public List<User> findUserByNickName(String queryName) {
        return em.createQuery("select m from User m where m.user_nickname = :queryName", User.class)
                .setParameter("queryName", queryName)
                .getResultList();
    }

    @Override
    public User findUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAllUser() {
        return em.createQuery("select m from User m", User.class)
                .getResultList();
    }

    @Override
    public Long updateUser(User updateUser) {

        User findUser = em.createQuery("select m from User m where m.user_email = :email", User.class)
                .setParameter("email", updateUser.getUser_email())
                .getSingleResult();

        findUser.setUser_nickname(updateUser.getUser_nickname());
        findUser.setUser_pw(updateUser.getUser_pw());

        return findUser.getId();
    }

    @Override
    public void increaseMoney(int totalMoney, Long userId) {
        User userById = findUserById(userId);
        userById.setMoney(userById.getMoney() + totalMoney);
    }

    @Override
    public User findUserByEmail(String email) {

        User findUser = null;

        try {
            findUser = em.createQuery("select m from User m where m.user_email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();

        } catch (NoResultException e) {
            log.info("동일한 Email User가 없음");
            log.info(String.valueOf(e));
        }

        return findUser;
    }

    @Override
    public DuplicatedStatus checkDuplicatedEmail(String email) {
        DuplicatedStatus duplicatedStatus;

        try {
            User findUser = em.createQuery("select m from User m where m.user_email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();

            duplicatedStatus = DuplicatedStatus.Valid;
        } catch (NoResultException e) {
            duplicatedStatus = DuplicatedStatus.Invalid;
        }

        return duplicatedStatus;
    }

    @Override
    public DuplicatedStatus checkDuplicatedNickname(String nickname) {
        return null;
    }
}
