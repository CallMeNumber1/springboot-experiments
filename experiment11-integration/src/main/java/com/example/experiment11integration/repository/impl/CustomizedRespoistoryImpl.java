package com.example.experiment11integration.repository.impl;

import com.example.experiment11integration.repository.CustomizedRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

public class CustomizedRespoistoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomizedRepository<T, ID> {
    private EntityManager entityManager;
    // ???
    public CustomizedRespoistoryImpl(JpaEntityInformation entityInformation,EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }
    @Override
    public T refresh(T t) {
        entityManager.refresh(t);
        return t;
    }
}
