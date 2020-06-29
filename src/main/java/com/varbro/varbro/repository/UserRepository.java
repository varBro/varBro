package com.varbro.varbro.repository;

import com.varbro.varbro.model.logistics.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "select * from user u join personal_information p on p.user_id = u.user_id where u.department like 'PRODUCTION'",
            nativeQuery=true)
    List<User> productionWorkers();
}
