package com.example.real_store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;


@EqualsAndHashCode
@ToString
@Data
public class BaseEntity {
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
