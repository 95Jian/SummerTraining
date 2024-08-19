package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/getAll")
    public Map<String, Object> getAllRoles() {
        Map<String, Object> response = new HashMap<>();
        List<Role> roles = roleService.getAllRoles();

        response.put("roles", roles);
        return response;
    }


    @PostMapping("/add")
    public Map<String, Object> addRole(@RequestBody Role role) {
        Map<String, Object> response = new HashMap<>();
        try {
            if(role.getName().length()==0){
                response.put("success", false);
                response.put("message", "角色名不能为空");
            }else {
                Role existingRole = roleService.findByRolename(role.getName());
                if (existingRole != null) {
                    response.put("success", false);
                    response.put("message", "角色名已存在");
                } else {
                    //可以插入
                    // 创建日期对象
                    Date date = new Date();
                    // 创建 SimpleDateFormat 对象，指定日期格式
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    // 格式化日期对象
                    String formattedDate = formatter.format(date);
                    role.setCreateTime(formattedDate);

                    roleService.saveRole(role);
                    response.put("success", true);
                }
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to add role");
        }
        return response;
    }

    @PutMapping("/update/{id}")
    public Map<String, Object> updateRole(@PathVariable int id, @RequestBody Role role) {
        Map<String, Object> response = new HashMap<>();
        try {
            if(role.getName().length()==0){
                response.put("success", false);
                response.put("message", "角色名不能为空");
                return response;
            }
            String initialRoleName=roleService.findRolenameById(id);
            if(!initialRoleName.equals(role.getName())){
                //角色名改了
                Role existingRole = roleService.findByRolename(role.getName());
                if (existingRole != null) {
                    response.put("success", false);
                    response.put("message", "角色名已存在");
                    return response;
                }else{
                    //用户名改了但不重复
                    role.setId(id);
                    roleService.updateRole(role);
                    response.put("success", true);
                }
            }else{
                //用户名没改
                role.setId(id);
                roleService.updateRole(role);
                response.put("success", true);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to update role");
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteRole(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            roleService.deleteRole(id);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to delete role");
        }
        return response;
    }
}
