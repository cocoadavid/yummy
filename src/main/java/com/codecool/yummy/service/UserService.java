package com.codecool.yummy.service;

import com.codecool.yummy.model.User;

/**
 * Created by szilarddavid on 2017.07.11..
 */
public interface UserService {
    public User findUserByEmail(String email);
    public User findUserByUsername(String username);
    public void saveUser(User user);
}
