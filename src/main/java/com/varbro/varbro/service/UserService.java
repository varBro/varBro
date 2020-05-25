package com.varbro.varbro.service;

import com.varbro.varbro.model.User;

public interface UserService {
    void save(User user);

    User findByEmail(String email);
}
