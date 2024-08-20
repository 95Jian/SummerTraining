package com.example.demo.controller;

import com.example.demo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    public static void sortStrings(List<String> list) {
        Collections.sort(list);
    }

    @GetMapping("/getRoles/{userId}")
    public Map<String, Object> getRolesByUserId(@PathVariable int userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Integer> roleIds = userRoleService.getRolesByUserId(userId);
            response.put("success", true);
            response.put("roles", roleIds);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to get Roles by user ID");
        }
        return response;
    }

    @GetMapping("/getRoleName/{userId}")
    public Map<String, Object> getRoleNameByUserId(@PathVariable int userId) {


        Map<String, Object> response = new HashMap<>();
        try {
            List<String> roles = userRoleService.getRoleNameByUserId(userId);
            sortStrings(roles);
            response.put("success", true);
            response.put("roles", roles);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to get roles by user ID");
        }
        return response;
    }


    @PostMapping("/add")
    public Map<String, Object> addRoleToUser(@RequestParam int userId, @RequestParam int roleId) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 检查角色是否已经拥有该权限
            boolean hasRole = userRoleService.checkRoleExists(userId, roleId);

            if (hasRole) {
                response.put("success", true);
            } else {
                // 如果没有该权限，才添加
                userRoleService.addRoleToUser(userId, roleId);
                response.put("success", true);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to add role to user");
        }
        return response;
    }

    @DeleteMapping("/delete")
    public Map<String, Object> removeRoleFromUser(@RequestParam int userId, @RequestParam int roleId) {
        Map<String, Object> response = new HashMap<>();
        try {
            userRoleService.removeRoleFromUser(userId, roleId);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to remove roleId from userId");
        }
        return response;
    }

    @PostMapping("/update/{userId}")
    public Map<String, Object> addRoles(@PathVariable int userId, @RequestBody List<Integer> roles) {
        Map<String, Object> response = new HashMap<>();
        try {
            userRoleService.updateRoles(userId, roles);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to save roles");
        }
        return response;
    }
}
