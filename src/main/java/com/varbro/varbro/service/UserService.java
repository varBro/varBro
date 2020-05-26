package com.varbro.varbro.service;

import com.varbro.varbro.model.User;
import com.varbro.varbro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void saveUser(User user) {

        userRepository.save(user);
    }

    public void saveUsers(List<User> users) {

        userRepository.saveAll(users);
    }

    public Iterable<User> getUsers() {

        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {

        return userRepository.findById(id);
    }

    public void deleteAll() {

        userRepository.deleteAll();
    }
}