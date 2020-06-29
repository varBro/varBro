package com.varbro.varbro.service;

import com.varbro.varbro.model.Role;
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

    public Iterable<User> getUsersOrderedBySurname() {
        return userRepository.findAllByOrderBySurname();
    }

    public Iterable<User> getUsersLikeSurname(String surname) {
        return userRepository.findBySurnameContainingIgnoreCase(surname);
    }

    public Iterable<User> getUsersLikeName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public Iterable<User> getUsersLikeNameOrLikeSurname(String name, String surname) {
        return userRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(name, surname);
    }

    public Optional<User> getUserById(Long id) {

        return userRepository.findById(id);
    }

    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public List<User> getProductionWorkers() {

        return userRepository.productionWorkers();
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

    public String changeStatus(String email) {

        try {
            User user = getUserByEmail(email);
            int status = user.getStatus();

            if (status > 1) {
                user.setStatus(status-1);
                saveUser(user);
                return "0";
            }
            else if (status == 1) {
                user.setStatus(status-1);
                saveUser(user);
                return "Account locked";
            } else if (status == 0) {
                return "Account locked";
            }
        } catch (Exception e) {
            return "No user found";
        }
        return "0";
    }

    public void resetStatus(String email) {
        User user = getUserByEmail(email);
        user.setStatus(3);
        saveUser(user);
    }
}