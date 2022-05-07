package com.example.real_store.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@TableName("t_user")
@EqualsAndHashCode
@ToString
@Data
public class User implements Serializable {
    @TableId
    private Integer uid;
    private String username;
    private String password ;
    private String salt;
    private String phone ;
    private String email;
    private Integer  gender ;
    private String avatar;


    @TableField("is_delete")
    private Integer isDelete;
    @TableField("created_user")
    private String createdUser;
    @TableField("created_time")
    private Date createdTime;
    @TableField("modified_user")
    private String modifiedUser;
    @TableField("modified_time")
    private Date modifiedTime;
}
