package com.example.real_store.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.real_store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


//标注当前类是测试类，不会打包发送
@SpringBootTest

//表示启动这个单元测试类，固定写法
@RunWith(SpringRunner.class)
public class UserMappingTest {

    /**
     * 必须被test修饰
     * 返回必须为void
     * 参数不指定任何类型
     * 必须为public
     */

//    接口不能直接创建bean（动态代理技术解决）
    @Autowired(required = false)
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("cby");
        user.setPassword("cby");
        Integer insert = userMapper.insert(user);
        System.out.println(insert);
    }


    @Test
    public void findBuUsername() {
//        User root = userMapper.findByUsername("root");
//        System.out.println(root);

    }

    @Test
    public void selectUser() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.like("username", "c").lt("uid", 5);

        List<User> users = userMapper.selectList(userQueryWrapper);

        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperTwo() {
        QueryWrapper<User> query = Wrappers.query();

        query.likeRight("username", "c").between("uid", 1, 3).orderByAsc("password");

        List<User> users = userMapper.selectList(query);

        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperThree() {
        QueryWrapper<User> query = Wrappers.query();

//        query.in("uid",1,2);

        query.in("uid", Arrays.asList(1, 2, 3));

        List<User> users = userMapper.selectList(query);

        users.forEach(System.out::println);
    }

    @Test
    public void update() {
        User user = new User();

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.eq("uid",2);

        user.setUsername("123");
        user.setPassword("222");
        user.setModifiedUser("管理员");
        user.setModifiedTime(new Date());

        int update = userMapper.update(user, userQueryWrapper);

        System.out.println("update"+update);
    }

}
