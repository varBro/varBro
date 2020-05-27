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

    public Optional<User> getUserById(Long id) {

        return userRepository.findById(id);
    }

    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public void delete(User user) {

        userRepository.delete(user);
    }

    public void deleteAll() {

        userRepository.deleteAll();
    }

    public User getOne(Long id) {

        return userRepository.getOne(id);
    }
}