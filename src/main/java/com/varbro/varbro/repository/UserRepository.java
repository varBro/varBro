package com.varbro.varbro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.varbro.varbro.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findAllByOrderBySurname();

    List<User> findBySurnameContainingIgnoreCase(String surname);

    List<User> findByNameContainingIgnoreCase(String name);

    List<User> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname);
}
