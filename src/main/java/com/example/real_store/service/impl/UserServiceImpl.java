package com.example.real_store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.real_store.entity.User;
import com.example.real_store.mapper.UserMapper;
import com.example.real_store.service.IUserService;
import com.example.real_store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.Wrapper;
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

        if (!md5Password.equals(user.getPassword())) {
            throw new PasswordNotMatchException("密码错误");
        }

        User result = new User();

        result.setUsername(username);
        result.setPassword(password);
        result.setSalt(user.getSalt());

        return result;

    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.eq("username", username);

        List<User> users = userMapper.selectList(userQueryWrapper);

        if (users.size() == 0) {
            throw new UserNotFoundException("用户数据不存在");
        }

        User user = users.get(0);

        if (user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }

        String md5Password = getMD5Password(oldPassword, user.getSalt());

        if (!md5Password.equals(user.getPassword())) {
            throw new PasswordNotMatchException("原密码错误");
        }

        String newPas = getMD5Password(newPassword, user.getSalt());

        user.setPassword(newPas);

        int i = userMapper.update(user, userQueryWrapper);

        if (i != 1) {
            throw new UpdateException("更新用户数据出现未知错误，请联系系统管理员");
        }

    }

    @Override
    public User getUserInfo(Integer uid) {
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("uid",uid);

        List<User> users = userMapper.selectByMap(hashMap);

        if (users.size()==0){
            throw new UserNotFoundException("用户数据不存在");
        }

        User user = users.get(0);

        User result = new User();

        if (user.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }

        result.setUsername(user.getUsername());
        result.setPhone(user.getPhone());
        result.setEmail(user.getEmail());
        result.setGender(user.getGender());

        return result;
    }

    @Override
    public void updateInfo(Integer uid,String username, User user) {
        User user1 = new User();

        user1.setUid(uid);

        user1 = userMapper.selectById(user1);

        if (user1==null){
            throw new UserNotFoundException("用户数据不存在");
        }
        if (user1.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }

        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        int i = userMapper.updateById(user);

        if(i!=1){
            throw new UpdateException("更新用户数据出现未知错误，请联系管理员");
        }


    }

    private String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            //        md5加密算法方法的调用
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toString();
        }
        return password;
    }
}
