package com.example.real_store.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@TableName("t_address")
@ToString
@EqualsAndHashCode
@Data
public class Address extends BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer aid;

    private Integer uid;
    private String name;

    @TableField("province_name")
    private String provinceName;
    @TableField("province_code")
    private String provinceCode;
    @TableField("city_name")
    private String cityName;
    @TableField("city_code")
    private String cityCode;
    @TableField("area_name")
    private String areaName;
    @TableField("area_code")
    private String areaCode;

    private String zip;

    private String address;

    private String phone;
    private String tel;
    private String tag;
    @TableField("is_default")
    private Integer isDefault;



}
