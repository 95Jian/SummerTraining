package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        // 直接保存密码为明文
        userMapper.insert(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }


    @Override
    public List<User> getPageUsers(int page, int size) {
        int offset = page * size;
        return userMapper.findUsers(offset, size);
    }


    public List<User> searchUsersByUsername(String username, int offset, int size) {
        return userMapper.findUsersByUsername(username, offset, size);
    }

    public int countUsers() {
        return userMapper.countUsers();
    }

    public int countUsersByUsername(String username) {
        return userMapper.countUsersByUsername(username);
    }

    @Override
    public void deleteUser(int id) {
        userMapper.deleteById(id);
    }


    @Override
    public void updateUser(User user) {
        String existingPassword = userMapper.getPasswordById(user.getId());
        user.setPassword(existingPassword);

        userMapper.updateUser(user);
    }


    @Override
    public String findUsernameById(int id) {
        User user = userMapper.selectById(id);
        return (user != null) ? user.getUsername() : null;
    }
}
