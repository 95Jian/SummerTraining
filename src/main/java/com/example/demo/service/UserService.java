package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    void saveUser(User user);
    List<User> getAllUsers();
    void deleteUser(int id);
    void updateUser(User user);
    String findUsernameById(int id);


}
