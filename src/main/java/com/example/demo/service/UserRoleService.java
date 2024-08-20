package com.example.demo.service;

import java.util.List;

public interface UserRoleService {

    Integer getRolesByUserId(int userId);

    void addRoleToUser(int userId, int roleId);

    void removeRoleFromUser(int userId, int roleId);

    boolean checkRoleExists(int userId, int roleId) ;

    String getRoleNameByUserId(int userId);

    void updateRole(int userId, Integer roleId);
}
