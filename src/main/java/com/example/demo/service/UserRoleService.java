package com.example.demo.service;

import java.util.List;

public interface UserRoleService {

    List<Integer> getRolesByUserId(int userId);

    void addRoleToUser(int userId, int roleId);

    void removeRoleFromUser(int userId, int roleId);

    boolean checkRoleExists(int userId, int roleId) ;

    List<String> getRoleNameByUserId(int userId);

    void updateRoles(int userId, List<Integer> roleIds);
}
