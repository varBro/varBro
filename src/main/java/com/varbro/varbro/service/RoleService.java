package com.varbro.varbro.service;

import com.varbro.varbro.model.Role;
import com.varbro.varbro.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public void saveRole(Role role) {

        roleRepository.save(role);
    }

    public void saveRoles(Set<Role> roles) {

        roleRepository.saveAll(roles);
    }

    public Optional<Role> getRole(Long id) {

        return roleRepository.findById(id);
    }

    public Iterable<Role> getRoles() {

        return roleRepository.findAll();
    }

    public void deleteAll() {

        roleRepository.deleteAll();
    }

}
