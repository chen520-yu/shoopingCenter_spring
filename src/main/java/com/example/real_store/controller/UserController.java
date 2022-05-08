package com.example.real_store.controller;


import com.example.real_store.controller.ex.*;
import com.example.real_store.entity.User;
import com.example.real_store.service.IUserService;
import com.example.real_store.service.ex.InsertException;
import com.example.real_store.service.ex.UsernameDuplicatedException;
import com.example.real_store.until.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);

        User userInfo = userService.getUserInfo(uid);

        return new JsonResult<User>(OK, userInfo);
    }


    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        String usernameFromSession = getUsernameFromSession(session);

        Integer uidFromSession = getUidFromSession(session);

        userService.updateInfo(uidFromSession, usernameFromSession, user);

        return new JsonResult<Void>(OK);
    }


    public static final int AVATAR_MAX_SIZE=10*1024*1024;
    public static final List<String> AVATARS_TYPES=new ArrayList<String>();

    static {
        AVATARS_TYPES.add("image/jpeg");
        AVATARS_TYPES.add("image/png");
        AVATARS_TYPES.add("image/bmp");
        AVATARS_TYPES.add("image/gif");
    }

    @PostMapping("change_info")
    public JsonResult<String> changeAvatar(@RequestParam("file")MultipartFile file,HttpSession session){
        if (file.isEmpty()){
            throw new FileEmptyException("上传的头像不允许为空");
        }
        if (file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("不允许上传超过"+AVATAR_MAX_SIZE/1024+"KB的头像文件");
        }
        String contentType = file.getContentType();
        if (!AVATARS_TYPES.contains(contentType)){
            throw new FileTypeException("不允许使用该类型的文件作为头像，运行的文件类型为"+AVATARS_TYPES.toString());
        }

//        获取当前项目的绝对磁盘路径
        String upload = session.getServletContext().getRealPath("upload");

        File dir = new File(upload);

//        保存头像的文件夹
        if (!dir.exists()){
            dir.mkdirs();
        }

//        保存的头像文件名
        String sufiix="";

        String originalFilename = file.getOriginalFilename();

        int beginIndex = originalFilename.lastIndexOf(".");

        if (beginIndex>0){
            sufiix=originalFilename.substring(beginIndex);
        }

        String filename = UUID.randomUUID().toString()+sufiix;

        File dest= new File(dir,filename);

        try {
            file.transferTo(dest);
        }catch (IllegalStateException e){
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        }catch (IOException e){
            throw new FileUpLoadException("上传文件时读写错误，请稍后重尝试");
        }

        String avatar ="/upload/"+filename;

        Integer uidFromSession = getUidFromSession(session);
        String usernameFromSession = getUsernameFromSession(session);
        userService.changeAvatar(uidFromSession,usernameFromSession,avatar);

        return new JsonResult<String>(OK,avatar);
    }

}
