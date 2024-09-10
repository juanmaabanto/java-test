package com.juanabanto.test.service;

import com.juanabanto.test.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    List<User> getAll();
    Optional<User> findByEmail(String email);
}
