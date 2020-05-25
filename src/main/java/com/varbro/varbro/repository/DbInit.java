package com.varbro.varbro.repository;

import com.varbro.varbro.model.Role;
import com.varbro.varbro.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DbInit implements CommandLineRunner {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public DbInit( RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

        this.roleRepository.deleteAll();
        this.userRepository.deleteAll();

        Role Employee = new Role("EMPLOYEE");
        Role Admin = new Role("ADMIN");
        Role Manager = new Role("MANAGER");
        Role Production = new Role("ROLE_PRODUCTION");
        Role Logistics = new Role("ROLE_LOGISTICS");
        Role Distribution = new Role("ROLE_DISTRIBUTION");
        Role HR = new Role("ROLE_HR");
        Role Finance = new Role("ROLE_FINANCE");
        Role IT = new Role("ROLE_IT");

        List<Role> roles = Arrays.asList(Employee, Admin,Manager,Production,Logistics,Distribution,HR,Finance,IT);


        User ADMIN = new User("Admin", "Admin", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "admin@admin.com", "213721372", 99999, User.Department.IT);
        User ADMIN1 = new User("Dummy", "Dummy", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "dummy@test.com", "777777777", 99999, User.Department.IT);

        //Role role = new Role();
        Set<Role> roleSet = new HashSet();
        //role.setRole("ADMIN");
        roleSet.add(Admin);
        roleSet.add(Employee);
        ADMIN.setRoles(roleSet);
        ADMIN1.setRoles(roleSet);

        List<User> users = Arrays.asList(ADMIN,ADMIN1);
        //this.roleRepository.saveAll(roles);
        this.userRepository.saveAll(users);
    }


}
