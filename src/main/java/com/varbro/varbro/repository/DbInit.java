package com.varbro.varbro.repository;

import com.varbro.varbro.model.Role;
import com.varbro.varbro.model.User;
import com.varbro.varbro.model.finance.Contractor;
import com.varbro.varbro.model.finance.Invoice;
import com.varbro.varbro.model.finance.InvoiceProduct;
import com.varbro.varbro.model.finance.Product;
import com.varbro.varbro.service.RoleService;
import com.varbro.varbro.service.UserService;
import com.varbro.varbro.service.finance.ContractorService;
import com.varbro.varbro.service.finance.InvoiceService;
import com.varbro.varbro.service.finance.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class DbInit implements CommandLineRunner {

    private UserService userService;
    private RoleService roleService;
    private InvoiceService invoiceService;
    private ContractorService contractorService;
    private ProductService productService;

    public DbInit(UserService userService, RoleService roleService, InvoiceService invoiceService, ContractorService contractorService, ProductService productService) {
        this.userService = userService;
        this.roleService = roleService;
        this.invoiceService = invoiceService;
        this.contractorService = contractorService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        this.userService.deleteAll();
        this.roleService.deleteAll();
        this.invoiceService.deleteAll();
        this.contractorService.deleteAll();
        this.productService.deleteAll();

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

        User ADMIN = new User("Admin", "Admin", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "admin@admin.com", "213721372", 99999, User.Department.IT);
        User ADMIN1 = new User("Dummy", "Dummy", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "dummy@test.com", "777777777", 99999, User.Department.IT);
        User JOHN = new User("John", "Doe", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "john.doe@gmail.com", "666666666", 99999, User.Department.PRODUCTION);
        User FINANCE_USER = new User("John", "Doe", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6","finance@varbro.com", "555555555", 99999, User.Department.FINANCE);

        ADMIN.setRoles(new HashSet(Arrays.asList(Employee, Admin)));
        ADMIN1.setRoles(new HashSet(Arrays.asList(Employee, Admin)));
        JOHN.setRoles(new HashSet(Arrays.asList(Employee, Production)));
        FINANCE_USER.setRoles(new HashSet(Arrays.asList(Employee,Finance)));

        List<User> users = Arrays.asList(ADMIN,ADMIN1,JOHN,FINANCE_USER);
        this.userService.saveUsers(users);

        Contractor contractor1 = new Contractor("JanuszeX","gdziestam","1234567890");
        Contractor contractor2 = new Contractor("HalineX","gdzieindziej","0987654321");
        List<Contractor> contractors = Arrays.asList(contractor1,contractor2);
        this.contractorService.saveContractors(contractors);

        Product product1 = new Product("Piwo",12.3);
        Product product2 = new Product("Wino",32.1);
        List<Product> products = Arrays.asList(product1,product2);
        this.productService.saveProducts(products);

        InvoiceProduct invoiceProduct1 = new InvoiceProduct(product1,3.0);
        InvoiceProduct invoiceProduct2 = new InvoiceProduct(product2, 4.0);

        Invoice invoice1 = new Invoice(LocalDate.now(),contractor1, Arrays.asList(invoiceProduct1, invoiceProduct2));
        Invoice invoice2 = new Invoice(LocalDate.now(),contractor2, Arrays.asList(invoiceProduct2, invoiceProduct1));
        List<Invoice> invoices = Arrays.asList(invoice1,invoice2);
        this.invoiceService.saveInvoices(invoices);
    }
}
