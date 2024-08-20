package com.example.demo.service.impl;

import com.example.demo.entity.UserRole;
import com.example.demo.mapper.UserRoleMapper;
import com.example.demo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void updateRole(int userId, Integer roleId) {
        userRoleMapper.deleteUserAllRole(userId);

            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRole.setCreateTime(new Date());
            userRoleMapper.insertUserRole(userRole);

    }

    @Override
    public String getRoleNameByUserId(int userId) {
        return userRoleMapper.getRoleNameByUserId(userId);
    }

    public boolean checkRoleExists(int userId, int roleID) {
        return userRoleMapper.countRole(userId, roleID) > 0;
    }

    public Integer getRolesByUserId(int userId) {
        return userRoleMapper.findRoldIdByUserId(userId);
    }

    public void addRoleToUser(int userId, int roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRole.setCreateTime(new Date());
        userRoleMapper.insertUserRole(userRole);
    }

    public void removeRoleFromUser(int userId, int roleId) {
        userRoleMapper.deleteUserRole(userId, roleId);
    }
}
