package com.codecool.yummy.domain.service;

import com.codecool.yummy.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by szilarddavid on 2017.07.05..
 */
@Stateless
public class UserService extends AbstractFacade<User>{

    @PersistenceContext(unitName = "yummyPU")
    private EntityManager em;

    public UserService() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
