package com.varbro.varbro.repository;

import com.varbro.varbro.model.logistics.Product;
import com.varbro.varbro.model.Role;
import com.varbro.varbro.model.User;
import com.varbro.varbro.model.logistics.Stock;
import com.varbro.varbro.model.production.Beer;
import com.varbro.varbro.model.production.BeerIngredient;
import com.varbro.varbro.service.RoleService;
import com.varbro.varbro.service.UserService;
import com.varbro.varbro.service.logistics.OrderService;
import com.varbro.varbro.service.logistics.ProductService;
import com.varbro.varbro.service.logistics.StockService;
import com.varbro.varbro.service.production.BeerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.varbro.varbro.model.logistics.Product.Unit.KG;
import static com.varbro.varbro.model.logistics.Product.Unit.PCS;

@Service
public class DbInit implements CommandLineRunner {

    private UserService userService;
    private RoleService roleService;
    private ProductService productService;
    private StockService stockService;
    private OrderService orderService;
    private BeerService beerService;

    public DbInit(UserService userService, RoleService roleService, ProductService productService,
            StockService stockService, OrderService orderService, BeerService beerService) {
        this.userService = userService;
        this.roleService = roleService;
        this.productService = productService;
        this.stockService = stockService;
        this.orderService = orderService;
        this.beerService = beerService;
    }

    @Override
    public void run(String... args) {

        this.userService.deleteAll();
        this.roleService.deleteAll();
        this.stockService.deleteAll();
        this.orderService.deleteAll();
        this.productService.deleteAll();
        this.beerService.deleteAll();

        Role Employee = new Role("EMPLOYEE");
        Role Admin = new Role("ROLE_ADMIN");
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
        User PanWIESIO = new User("Wiesław", "Puchacki", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "panwiesio@gmail.com", "667647112", User.Department.PRODUCTION, "21370012146420753482567890", 8000, "590422475", User.Position.MANAGER);
        ADMIN.setRoles(new HashSet(Arrays.asList(Employee, Admin)));
        ADMIN1.setRoles(new HashSet(Arrays.asList(Employee, Admin)));
        JOHN.setRoles(new HashSet(Arrays.asList(Employee, Production)));
        JP.setRoles(new HashSet(Arrays.asList(Employee, Finance)));
        BARBARA.setRoles(new HashSet(Arrays.asList(Employee, HR, Manager)));
        WIESIO.setRoles(new HashSet(Arrays.asList(Employee, Logistics, Manager)));
        PanWIESIO.setRoles(new HashSet(Arrays.asList(Employee, Production, Manager)));

        List<User> users = Arrays.asList(ADMIN, ADMIN1, JOHN, JP, BARBARA, WIESIO, PanWIESIO);

        this.userService.saveUsers(users);

        Product BARLEY = new Product("Barley", KG, true);
        Product HOPS = new Product("Hops", KG, true);
        Product YEAST= new Product("Yeast", KG, true);
        Product BOTTLE = new Product("Bottle", PCS);

        List<Product> products = Arrays.asList(BARLEY, HOPS, YEAST, BOTTLE);
        this.productService.saveProducts(products);

        Stock BARLEY_STOCK = new Stock(BARLEY, 158.5);
        Stock HOPS_STOCK = new Stock(HOPS, 123.5);
        Stock YEAST_STOCK = new Stock(YEAST, 56.2);
        Stock BOTTLE_STOCK = new Stock(BOTTLE, 5602);

        List<Stock> stocks = Arrays.asList(BARLEY_STOCK, HOPS_STOCK, YEAST_STOCK, BOTTLE_STOCK);
        this.stockService.saveStocks(stocks);

        beerService.saveBeer(new Beer("Pilsner", new BeerIngredient(HOPS, 5), new BeerIngredient(YEAST, 2)));
        Beer Ipa = new Beer("IPA");
        BeerIngredient ipaIngredient1 = new BeerIngredient(YEAST, 3);
        ipaIngredient1.setBeer(Ipa);
        Ipa.getBeerIngredients().add(ipaIngredient1);
        beerService.saveBeer(Ipa);
    }


}
