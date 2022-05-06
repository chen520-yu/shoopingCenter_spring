package com.example.real_store.controller;


import com.example.real_store.entity.User;
import com.example.real_store.service.IUserService;
import com.example.real_store.service.ex.InsertException;
import com.example.real_store.service.ex.UsernameDuplicatedException;
import com.example.real_store.until.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<Void>(OK);
    }


    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User login = userService.login(username, password);

        session.setAttribute("uid", login.getUid());
        session.setAttribute("username", login.getUsername());

        return new JsonResult<User>(OK, login);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {

        String username = getUsernameFromSession(session);

        userService.changePassword(username, oldPassword, newPassword);

        return new JsonResult<Void>(OK);
    }


}
