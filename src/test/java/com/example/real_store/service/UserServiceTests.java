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
    public void test(){
        try {
            User user = new User();
            user.setUsername("anna");
            user.setPassword("anna");
            iUserService.reg(user);
        }catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

}
