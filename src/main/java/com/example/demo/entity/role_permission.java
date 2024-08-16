package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class role_permission {

    @TableId(value = "id",type= IdType.AUTO)
    private int id;
    private int role_id;
    private int permission_id;
    private Date create_time;
}
