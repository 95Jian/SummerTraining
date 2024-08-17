package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    @TableId(value = "id",type= IdType.AUTO)
    private int id;

    private String user_name;

    private String password;

    private String avatar_path;

    private String phone;

    private String email;

    private String gender;

    private String address;

    private String introduction;

    private Date create_time;

}
