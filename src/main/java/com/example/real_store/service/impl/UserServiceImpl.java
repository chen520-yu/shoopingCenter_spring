package com.example.real_store.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.real_store.entity.User;
import com.example.real_store.mapper.UserMapper;
import com.example.real_store.service.IUserService;
import com.example.real_store.service.ex.InsertException;
import com.example.real_store.service.ex.PasswordNotMatchException;
import com.example.real_store.service.ex.UserNotFoundException;
import com.example.real_store.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public void reg(User user) {

//        判断用户是否已经注册过
        String username = user.getUsername();

        HashMap<String, Object> params = new HashMap<>();
        params.put("username", username);

        List<User> result = userMapper.selectByMap(params);

        if (result.size() != 0) {
            throw new UsernameDuplicatedException("用户名已经被占用");
        }
        String oldpassword = user.getPassword();

//        md5密码加密算法，盐值+psw+盐值
//        获取盐值
        String salt = UUID.randomUUID().toString().toUpperCase();

        user.setSalt(salt);
        System.out.println(salt);
        String md5Password = getMD5Password(oldpassword, salt);

        user.setPassword(md5Password);
        System.out.println(md5Password);


        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setModifiedTime(date);
        user.setCreatedTime(date);
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("在用户插入过程中出现了未知异常");
        }


    }

    @Override
    public User login(String username, String password) {
        QueryWrapper<User> query = Wrappers.query();

        query.eq("username", username);

        List<User> users = userMapper.selectList(query);

        if (users.size() == 0) {
            throw new UserNotFoundException("用户不存在");
        }

        User user = users.get(0);

        if (user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }

        String md5Password = getMD5Password(password, user.getSalt());

        if (md5Password!=user.getPassword()){
            throw new PasswordNotMatchException("密码错误");
        }

        User result=new User();

        result.setUsername(username);
        result.setPassword(password);
        result.setSalt(user.getSalt());

        return result;

    }

    private String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            //        md5加密算法方法的调用
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toString();
        }
        return password;
    }
}
