package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class user_role {

    @TableId(value = "id",type= IdType.AUTO)
    private int id;
    private int user_id;
    private int role_id;
    private Date create_time;
}
