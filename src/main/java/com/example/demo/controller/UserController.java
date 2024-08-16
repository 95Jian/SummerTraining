package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        try {
            User existingUser = userService.findByUsername(user.getUser_name());
            if (existingUser != null) {
                //用户名重复
                response.put("success", false);
                response.put("message", "用户名已存在");
            } else {
                //可以插入
                user.setCreate_time(new Date());
                userService.saveUser(user);
                response.put("success", true);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "注册失败");
        }
        return response;
    }
}
