package com.example.buyme.service;

import com.example.buyme.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User saveUser(User user);

    public List<User> findAllUsers();

    public Optional<User> findUserById(String username);

}
