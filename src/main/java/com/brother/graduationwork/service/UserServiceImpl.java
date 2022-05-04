package com.brother.graduationwork.repository;

import com.brother.graduationwork.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class h2Database implements UserService {

    @PersistenceContext
    EntityManager em;

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
}
