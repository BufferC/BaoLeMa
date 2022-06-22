package com.fc.service;

import com.fc.entity.User;

public interface UserService {
    User getByPhone(String phone);

    boolean save(User user);

    User getById(Long userId);
}
