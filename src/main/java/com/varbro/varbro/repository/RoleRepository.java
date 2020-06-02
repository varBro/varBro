package com.varbro.varbro.repository;

import com.varbro.varbro.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  RoleRepository extends JpaRepository<Role, Long> {
    public Role findByRole(String role);
}
