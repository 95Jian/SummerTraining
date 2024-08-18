package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    void saveUser(User user);
    List<User> getAllUsers();
    List<User> getPageUsers(int page, int size);
    void deleteUser(int id);
    void updateUser(User user);
    String findUsernameById(int id);
    int countUsers();

}
