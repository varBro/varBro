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

        User ADMIN = new User("Admin", "Admin", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "admin@admin.com", "213721372", User.Department.IT, "12345678901234567890123456", 99999, "12345678901");
        User ADMIN1 = new User("Dummy", "Dummy", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "dummy@test.com", "777777777", User.Department.IT, "09876543210987654321123456", 99999, "09876543211");
        User JOHN = new User("John", "Doe", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "john.doe@gmail.com", "666666666", User.Department.PRODUCTION, "12345678900987654321123456", 99999, "09876123455");
        User Foo = new User("Foo", "Bar", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "foo.bar@gmail.com", "666666646", User.Department.HR, "12345674900987654321123456", 10000, "09876123255");
        User JP = new User("Jan", "Pawel", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "janpawel2@gmail.com", "213721377", User.Department.FINANCE, "21370012345678901234567890", 2137, "21372137213");

        ADMIN.setRoles(new HashSet(Arrays.asList(Employee, Admin)));
        ADMIN1.setRoles(new HashSet(Arrays.asList(Employee, Admin)));
        JOHN.setRoles(new HashSet(Arrays.asList(Employee, Production)));
        Foo.setRoles(new HashSet(Arrays.asList(Employee, HR)));
        JP.setRoles(new HashSet(Arrays.asList(Employee, Finance)));

        List<User> users = Arrays.asList(ADMIN,ADMIN1,JOHN, Foo, JP);

        this.userService.saveUsers(users);

    }


}
