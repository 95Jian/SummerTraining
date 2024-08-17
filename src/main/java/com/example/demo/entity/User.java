package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    @TableId(value = "id",type= IdType.AUTO)
    private int id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "avatarPath")
    private String avatarPath;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "email")
    private String email;

    @TableField(value = "gender")
    private String gender;

    @TableField(value = "address")
    private String address;

    @TableField(value = "introduction")
    private String introduction;

    @TableField(value = "createTime")
    private String  createTime;

}
