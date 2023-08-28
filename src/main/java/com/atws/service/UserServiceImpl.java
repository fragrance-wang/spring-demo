package com.atws.service;

import com.atws.dao.UserDao;

public class UserServiceImpl implements UserService {

    public void setUserDao(UserDao userDao) {
        System.out.println("userDao: " + userDao);
    }
}
