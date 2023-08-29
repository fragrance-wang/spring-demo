package com.atws.test.bean;

public class UserService {

    private String name;

    private String uId;

    private UserDao userDao;

    public UserService() {
    }

    public String queryUserInfo() {
        // System.out.println("查询用户信息：" + userDao.queryUserName(uId));
        return userDao.queryUserName(uId);
    }

    public UserService(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("").append(name);
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}