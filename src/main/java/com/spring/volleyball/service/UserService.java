package com.spring.volleyball.service;

import com.spring.volleyball.model.User;

public interface UserService {
    void saveUser(User user);
    User getUserById(int id);
    void updateUser(User user);
    void deletedUserById(int id);
    User getUserByEmail(String email);
}