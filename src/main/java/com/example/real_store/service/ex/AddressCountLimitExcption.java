package com.example.real_store.service.ex;

import com.example.real_store.entity.User;
import com.example.real_store.service.impl.UserServiceImpl;

import java.io.Serializable;

public class AddressCountLimitExcption extends UserServiceImpl {
    @Override
    public void reg(User user) {
        super.reg(user);
    }

    @Override
    public User login(String username, String password) {
        return super.login(username, password);
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        super.changePassword(username, oldPassword, newPassword);
    }

    @Override
    public User getUserInfo(Integer uid) {
        return super.getUserInfo(uid);
    }

    @Override
    public void updateInfo(Integer uid, String username, User user) {
        super.updateInfo(uid, username, user);
    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        super.changeAvatar(uid, username, avatar);
    }
}
