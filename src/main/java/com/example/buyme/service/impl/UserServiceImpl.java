package com.example.buyme.service.impl;

import com.example.buyme.model.User;
import com.example.buyme.repository.UserRepository;
import com.example.buyme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        Sort sort = Sort.by(Sort.Direction.ASC, "username");
        return userRepository.findAll(sort);
    }

    @Override
    public Optional<User> findUserById(String username) {
        return userRepository.findById(username);
    }

}
