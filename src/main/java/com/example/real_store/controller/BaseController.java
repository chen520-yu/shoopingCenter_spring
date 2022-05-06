package com.example.real_store.controller;

import com.example.real_store.service.ex.*;
import com.example.real_store.until.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {

    public static final int OK = 200;

    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("uid").toString();
    }

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
        } else if (e instanceof InsertException) {
            result.setState(5000);
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4001);
        } else if (e instanceof UserNotFoundException) {
            result.setState(4002);
        }
        return result;
    }

}
