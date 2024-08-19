package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role findByRolename(String name) {
        return roleMapper.findByRolename(name);
    }

    @Override
    public void saveRole(Role role) { roleMapper.insertRole(role);    }

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.findAllRoles();
    }

    @Override
    public Role getRoleById(int id) {
        return roleMapper.findRoleById(id);
    }


    @Override
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }

    @Override
    public void deleteRole(int id) {
        roleMapper.deleteRole(id);
    }


    @Override
    public String findRolenameById(int id) {
        Role role = roleMapper.findRoleById(id);
        return (role != null) ? role.getName() : null;
    }
}
