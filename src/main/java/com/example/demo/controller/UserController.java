package com.example.demo.controller;

import com.example.demo.entity.LoginLog;
import com.example.demo.entity.User;
import com.example.demo.service.LoginLogService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser == null) {
            response.put("success", false);
            response.put("message", "用户不存在");
        } else if (user.getPassword().equals(existingUser.getPassword())) {
            response.put("success", true);

            // 返回用户信息给前端
            response.put("user", existingUser);
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
            if(user.getUsername().length()==0){
                response.put("success", false);
                response.put("message", "用户名不能为空");
            }else if(user.getPassword().length()==0){
                response.put("success", false);
                response.put("message", "密码不能为空");
            }else{
                User existingUser = userService.findByUsername(user.getUsername());
                if (existingUser != null) {
                    //用户名重复
                    response.put("success", false);
                    response.put("message", "用户名已存在");
                } else {
                    //可以插入
                    // 创建日期对象
                    Date date = new Date();
                    // 创建 SimpleDateFormat 对象，指定日期格式
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    // 格式化日期对象
                    String formattedDate = formatter.format(date);
                    user.setCreateTime(formattedDate);

                    userService.saveUser(user);
                    response.put("success", true);
                }
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "注册失败");
        }
        return response;
    }


    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteUser(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            userService.deleteUser(id);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Deleting user failed");
        }
        return response;
    }
    @PutMapping("/update/{id}")
    public Map<String, Object> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        try {
            if(user.getUsername().length()==0){
                response.put("success", false);
                response.put("message", "用户名不能为空");
                return response;
            }
            String initialUserName=userService.findUsernameById(id);
            if(!initialUserName.equals(user.getUsername())){
                //用户名改了
                User existingUser = userService.findByUsername(user.getUsername());
                if (existingUser != null) {
                    response.put("success", false);
                    response.put("message", "用户名已存在");
                    return response;
                }else{
                    //用户名改了但不重复
                    user.setId(id);
                    userService.updateUser(user);
                    response.put("success", true);
                }
            }else{
                //用户名没改
                user.setId(id);
                userService.updateUser(user);
                response.put("success", true);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "更新用户信息失败: " + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
