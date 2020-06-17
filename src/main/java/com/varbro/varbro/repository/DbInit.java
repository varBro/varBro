package com.varbro.varbro.repository;

import com.varbro.varbro.model.logistics.Commodity;
import com.varbro.varbro.model.Role;
import com.varbro.varbro.model.User;
import com.varbro.varbro.service.RoleService;
import com.varbro.varbro.service.UserService;
import com.varbro.varbro.service.logistics.CommodityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DbInit implements CommandLineRunner {

    private UserService userService;
    private RoleService roleService;
    private CommodityService commodityService;

    public DbInit(UserService userService, RoleService roleService, CommodityService commodityService) {
        this.userService = userService;
        this.roleService = roleService;
        this.commodityService = commodityService;
    }

    @Override
    public void run(String... args) {

        this.userService.deleteAll();
        this.roleService.deleteAll();
        this.commodityService.deleteAll();

        Role Employee = new Role("EMPLOYEE");
        Role Admin = new Role("ADMIN");
        Role Manager = new Role("MANAGER");
        Role Production = new Role("ROLE_PRODUCTION");
        Role Logistics = new Role("ROLE_LOGISTICS");
        Role Distribution = new Role("ROLE_DISTRIBUTION");
        Role HR = new Role("ROLE_HR");
        Role Finance = new Role("ROLE_FINANCE");
        Role IT = new Role("ROLE_IT");

        Set<Role> roles = new HashSet(Arrays.asList(Employee, Admin, Manager, Production, Logistics, Distribution, HR, Finance, IT));

        this.roleService.saveRoles(roles);

        User ADMIN = new User("Admin", "Admin", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "admin@admin.com", "213721372", User.Department.IT, "12345678901234567890123456", 99999, "12345678901", User.Position.ADMIN);
        User ADMIN1 = new User("Dummy", "Dummy", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "dummy@test.com", "777777777", User.Department.IT, "09876543210987654321123456", 99999, "09876543211", User.Position.ADMIN);
        User JOHN = new User("John", "Doe", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "john.doe@gmail.com", "666666666", User.Department.PRODUCTION, "12345678900987654321123456", 99999, "09876123455");
        User JP = new User("Jan", "Pawel", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "janpawel2@gmail.com", "213721377", User.Department.FINANCE, "21370012345678901234567890", 2137, "21372137213");
        User BARBARA = new User("Baśka", "Kwarc", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "bacha@gmail.com", "666997112", User.Department.HR, "21370012345648592474567890", 3137, "690628475", User.Position.MANAGER);
        User WIESIO = new User("Wiesław", "Paleta", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "wpaleta@gmail.com", "663427112", User.Department.LOGISTICS, "21370012146824753482567890", 5137, "590427475", User.Position.MANAGER);

        ADMIN.setRoles(new HashSet(Arrays.asList(Employee, Admin)));
        ADMIN1.setRoles(new HashSet(Arrays.asList(Employee, Admin)));
        JOHN.setRoles(new HashSet(Arrays.asList(Employee, Production)));
        JP.setRoles(new HashSet(Arrays.asList(Employee, Finance)));
        BARBARA.setRoles(new HashSet(Arrays.asList(Employee, HR, Manager)));
        WIESIO.setRoles(new HashSet(Arrays.asList(Employee, Logistics, Manager)));

        List<User> users = Arrays.asList(ADMIN, ADMIN1, JOHN, JP, BARBARA, WIESIO);

        this.userService.saveUsers(users);

        Commodity BARLEY = new Commodity("Barley", 150.7);
        Commodity HOPS = new Commodity("Hops", 40.2);
        Commodity YEAST= new Commodity("Yeast", 68.0);
        Commodity BOTTLE = new Commodity("Bottle", 1294);

        List<Commodity> commodities = Arrays.asList(BARLEY, HOPS, YEAST, BOTTLE);
        this.commodityService.saveCommodities(commodities);
    }


}
