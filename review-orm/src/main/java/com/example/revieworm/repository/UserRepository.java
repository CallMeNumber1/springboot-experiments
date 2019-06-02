package com.example.revieworm.repository;

import com.example.revieworm.entity.Address;
import com.example.revieworm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class UserRepository {
    // 注意点1
    @PersistenceContext
    private EntityManager em;

    public void addUserAddress() {
        User user = new User("BO");
        em.persist(user);
        // 注意点2，维护关系
        Address a1 = new Address("925");
        a1.setUser(user);
        em.persist(a1);
        Address a2 = new Address("956");
        a2.setUser(user);
        em.persist(a2);

    }
}
