package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Permission {

    @TableId(value = "id",type= IdType.AUTO)
    private int id;

    //权限名称
    private String name;

    //权限描述
    private String description;

}
