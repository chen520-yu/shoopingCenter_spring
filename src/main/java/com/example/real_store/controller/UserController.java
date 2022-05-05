package com.example.real_store.controller;


import com.example.real_store.entity.User;
import com.example.real_store.service.IUserService;
import com.example.real_store.service.ex.InsertException;
import com.example.real_store.service.ex.UsernameDuplicatedException;
import com.example.real_store.until.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
