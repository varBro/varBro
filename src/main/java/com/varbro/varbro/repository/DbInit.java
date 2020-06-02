package com.varbro.varbro.repository;

import com.varbro.varbro.model.Role;
import com.varbro.varbro.model.User;
import com.varbro.varbro.service.RoleService;
import com.varbro.varbro.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DbInit implements CommandLineRunner {

    private UserService userService;
    private RoleService roleService;

    public DbInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) {
        
        this.userService.deleteAll();
        this.roleService.deleteAll();

        Role Employee = new Role("EMPLOYEE");
        Role Admin = new Role("ADMIN");
        Role Manager = new Role("MANAGER");
        Role Production = new Role("ROLE_PRODUCTION");
        Role Logistics = new Role("ROLE_LOGISTICS");
        Role Distribution = new Role("ROLE_DISTRIBUTION");
        Role HR = new Role("ROLE_HR");
        Role Finance = new Role("ROLE_FINANCE");
        Role IT = new Role("ROLE_IT");

        Set<Role> roles = new HashSet(Arrays.asList(Employee,Admin,Manager,Production,Logistics,Distribution,HR,Finance,IT));

        this.roleService.saveRoles(roles);

        User ADMIN = new User("Admin", "Admin", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6",
                "admin@admin.com", "213721372", 99999, User.Department.IT, User.Position.ADMIN);
        User ADMIN1 = new User("Dummy", "Dummy", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6",
                "dummy@test.com", "777777777", 99999, User.Department.IT, User.Position.ADMIN);
        User JOHN = new User("John", "Doe", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6",
                "john.doe@gmail.com", "666666666", 99999, User.Department.PRODUCTION);
        User BASKA = new User("Pani", "Basia", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6",
                "baska@gmail.com", "666666666", 99999, User.Department.HR);

        ADMIN.setRoles(new HashSet(Arrays.asList(Employee, Admin)));
        ADMIN1.setRoles(new HashSet(Arrays.asList(Employee, Admin)));
        JOHN.setRoles(new HashSet(Arrays.asList(Employee, Production)));
        BASKA.setRoles(new HashSet(Arrays.asList(Employee, HR)));

        List<User> users = Arrays.asList(ADMIN,ADMIN1,JOHN, BASKA);

        this.userService.saveUsers(users);

    }


}
