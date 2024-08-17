package com.example.demo.controller;

import com.example.demo.entity.LoginLog;
import com.example.demo.entity.User;
import com.example.demo.service.LoginLogService;
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

    @Autowired
    private LoginLogService loginLogService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        User existingUser = userService.findByUsername(user.getUser_name());
        if (existingUser == null) {
            response.put("success", false);
            response.put("message", "用户不存在");
        } else if (user.getPassword().equals(existingUser.getPassword())) {
            response.put("success", true);
            // 添加登录记录
            LoginLog loginLog = new LoginLog();
            loginLog.setUserid(existingUser.getId());
            loginLog.setLogin_time(new Date());
            loginLogService.saveLoginLog(loginLog);
        } else {
            response.put("success", false);
            response.put("message", "密码错误");
        }
        return response;
    }


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
