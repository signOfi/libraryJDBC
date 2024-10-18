package com.banking.app.service;

import com.banking.app.entity.User;

public interface UserDao {
    void register(String username, String password);
    User login(String username, String password);
}
