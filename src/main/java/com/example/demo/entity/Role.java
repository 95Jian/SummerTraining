package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Role {

    //

    @TableId(value = "id",type= IdType.AUTO)
    private int id;

    private String name;

    private String description;

    private String createTime;


}
