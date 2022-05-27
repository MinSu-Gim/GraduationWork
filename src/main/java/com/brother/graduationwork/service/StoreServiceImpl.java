//package com.brother.graduationwork.service;
//
//import com.brother.graduationwork.domain.Menu;
//import com.brother.graduationwork.domain.Store;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//@Slf4j
//@Repository
//@Transactional
//public class StoreServiceImpl implements StoreService {
//
//    @PersistenceContext
//    EntityManager em;
//
//    @Override
//    public Long registerStore(Store store) {
//        em.persist(store);
//        return store.getId();
//    }
//
//    @Override
//    public Long registerMenu(Menu menu) {
//        em.persist(menu);
//        return menu.getId();
//    }
//}
