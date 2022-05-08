package com.example.real_store.service;

import com.example.real_store.entity.User;

public interface IUserService {

    void reg(User user);

    User login(String username,String password);

    void changePassword(String username,String oldPassword,String newPassword);

    User getUserInfo(Integer uid);

    void updateInfo(Integer uid,String username,User user);


    void changeAvatar(Integer uid,String username,String avatar);
}
