package com.example.demo.controller;

import com.example.demo.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/permission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    public static void sortStrings(List<String> list) {
        Collections.sort(list);
    }

    @GetMapping("/getPermissions/{roleId}")
    public Map<String, Object> getPermissionsByRoleId(@PathVariable int roleId) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Integer> permissions = rolePermissionService.getPermissionsByRoleId(roleId);
            response.put("success", true);
            response.put("permissions", permissions);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to get permissions by role ID");
        }
        return response;
    }

    @GetMapping("/getPermissionName/{roleId}")
    public Map<String, Object> getPermissionNameByRoleId(@PathVariable int roleId) {


        Map<String, Object> response = new HashMap<>();
        try {
            List<String> permissions = rolePermissionService.getPermissionNameByRoleId(roleId);
            sortStrings(permissions);
            response.put("success", true);
            response.put("permissions", permissions);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to get permissions by role ID");
        }
        return response;
    }


    @PostMapping("/add")
    public Map<String, Object> addPermissionToRole(@RequestParam int roleId, @RequestParam int permissionId) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 检查角色是否已经拥有该权限
            boolean hasPermission = rolePermissionService.checkPermissionExists(roleId, permissionId);

            if (hasPermission) {
                response.put("success", true);
            } else {
                // 如果没有该权限，才添加
                rolePermissionService.addPermissionToRole(roleId, permissionId);
                response.put("success", true);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to add permission to role");
        }
        return response;
    }

    @DeleteMapping("/delete")
    public Map<String, Object> removePermissionFromRole(@RequestParam int roleId, @RequestParam int permissionId) {
        Map<String, Object> response = new HashMap<>();
        try {
            rolePermissionService.removePermissionFromRole(roleId, permissionId);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to remove permission from role");
        }
        return response;
    }

    @PostMapping("/update/{roleId}")
    public Map<String, Object> addPermissions(@PathVariable int roleId, @RequestBody List<Integer> permissions) {
        Map<String, Object> response = new HashMap<>();
        try {
            rolePermissionService.updatePermissions(roleId, permissions);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to save permissions");
        }
        return response;
    }
}
