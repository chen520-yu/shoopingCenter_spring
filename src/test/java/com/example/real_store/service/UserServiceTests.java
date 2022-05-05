package com.example.real_store.service;


import com.example.real_store.entity.User;
import com.example.real_store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired(required = false)
    private IUserService iUserService;

    @Test
    public void test() {
        try {
            User user = new User();
            user.setUsername("yyy");
            user.setPassword("yyy");
            iUserService.reg(user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login() {
        try {
            String username = "test";
            String password = "123";
            User user = iUserService.login(username, password);
            System.out.println("注册成功");
        } catch (ServiceException e) {
            System.out.println("登录失败"+e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

    }

}
