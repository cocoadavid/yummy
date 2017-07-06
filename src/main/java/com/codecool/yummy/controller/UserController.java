package com.codecool.yummy.controller;

import com.codecool.yummy.domain.service.UserService;
import com.codecool.yummy.model.User;

import javax.ejb.EJB;
import java.util.List;

/**
 * Created by szilarddavid on 2017.07.05..
 */
public class UserController {
    private List<User> users;

    @EJB
    private UserService userService;

    public List<User> getUsers() {
        return userService.findAll();
    }
}
