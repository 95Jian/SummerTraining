package com.example.demo.service;

import com.example.demo.entity.RolePermission;

import java.util.Date;
import java.util.List;


public interface RolePermissionService {

    List<Integer> getPermissionsByRoleId(int roleId);

    void addPermissionToRole(int roleId, int permissionId);

    void removePermissionFromRole(int roleId, int permissionId);

    boolean checkPermissionExists(int roleId, int permissionId) ;

    List<String> getPermissionNameByRoleId(int roleId);

    void updatePermissions(int roleId, List<Integer> permissionIds);
}
