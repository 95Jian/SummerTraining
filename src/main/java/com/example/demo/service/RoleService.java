package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;

import java.util.List;

public interface RoleService {

    Role findByRolename(String name);

    void saveRole(Role role);

    List<Role> getAllRoles();


    Role getRoleById(int id);


    void updateRole(Role role);


    void deleteRole(int id) ;

    String findRolenameById(int id);
}
