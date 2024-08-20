package com.example.demo.service.impl;

import com.example.demo.entity.RolePermission;
import com.example.demo.mapper.RolePermissionMapper;
import com.example.demo.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public void updatePermissions(int roleId, List<Integer> permissionIds) {
        rolePermissionMapper.deleteRoleAllPermission(roleId);
        for (Integer permission : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permission);
            rolePermission.setCreateTime(new Date());
            rolePermissionMapper.insertRolePermission(rolePermission);
        }
    }

    @Override
    public List<String> getPermissionNameByRoleId(int roleId) {
        return rolePermissionMapper.getPermissionNameByRoleId(roleId);
    }

    public boolean checkPermissionExists(int roleId, int permissionId) {
        return rolePermissionMapper.countPermission(roleId, permissionId) > 0;
    }

    public List<Integer> getPermissionsByRoleId(int roleId) {
        return rolePermissionMapper.findPermissionsByRoleId(roleId);
    }

    public void addPermissionToRole(int roleId, int permissionId) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        rolePermission.setCreateTime(new Date());
        rolePermissionMapper.insertRolePermission(rolePermission);
    }

    public void removePermissionFromRole(int roleId, int permissionId) {
        rolePermissionMapper.deleteRolePermission(roleId, permissionId);
    }
}
