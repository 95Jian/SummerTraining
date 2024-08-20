package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class UserRole {

    @TableId(value = "id",type= IdType.AUTO)
    private int id;
    private int userId;
    private int roleId;
    private Date createTime;
}
