package com.example.real_store.controller;

import com.example.real_store.service.ex.InsertException;
import com.example.real_store.service.ex.ServiceException;
import com.example.real_store.service.ex.UsernameDuplicatedException;
import com.example.real_store.until.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    public static final int OK = 200;


    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
        } else if (e instanceof InsertException) {
            result.setState(5000);
        }
        return result;
    }

}
