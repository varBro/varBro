package com.varbro.varbro.repository;

import com.varbro.varbro.model.finance.ContractorFinance;
import com.varbro.varbro.model.finance.Invoice;
import com.varbro.varbro.model.finance.InvoiceProduct;
import com.varbro.varbro.model.finance.ProductFinance;
import com.varbro.varbro.model.logistics.*;
import com.varbro.varbro.model.Role;
import com.varbro.varbro.model.User;
import com.varbro.varbro.model.logistics.Product;
import com.varbro.varbro.model.production.Beer;
import com.varbro.varbro.model.production.BeerIngredient;
import com.varbro.varbro.model.production.Vat;
import com.varbro.varbro.service.RoleService;
import com.varbro.varbro.service.UserService;
import com.varbro.varbro.service.finance.ContractorServiceFinance;
import com.varbro.varbro.service.finance.InvoiceService;
import com.varbro.varbro.service.finance.ProductServiceFinance;
import com.varbro.varbro.service.logistics.ContractorService;
import com.varbro.varbro.service.logistics.OrderService;
import com.varbro.varbro.service.logistics.ProductService;
import com.varbro.varbro.service.logistics.StockService;
import com.varbro.varbro.service.production.BeerService;
import com.varbro.varbro.service.production.RequestService;
import com.varbro.varbro.service.production.VatService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    private ContractorService contractorService;
    private BeerService beerService;
    private RequestService requestService;
    private VatService vatService;
    private InvoiceService invoiceService;
    private ProductServiceFinance productServiceFinance;
    private ContractorServiceFinance contractorServiceFinance;


    public DbInit(UserService userService, RoleService roleService, ProductService productService,
                  StockService stockService, OrderService orderService, ContractorService contractorService,
                  BeerService beerService, RequestService requestService, InvoiceService invoiceService,
                  VatService vatService, ProductServiceFinance productServiceFinance, ContractorServiceFinance contractorServiceFinance) {
        this.userService = userService;
        this.roleService = roleService;
        this.productService = productService;
        this.stockService = stockService;
        this.orderService = orderService;
        this.contractorService = contractorService;
        this.beerService = beerService;
        this.requestService = requestService;
        this.vatService = vatService;
        this.invoiceService = invoiceService;
        this.productServiceFinance = productServiceFinance;
        this.contractorServiceFinance = contractorServiceFinance;
    }


    @Override
    public void run(String... args) {

        this.vatService.deleteAll();
        this.userService.deleteAll();
        this.roleService.deleteAll();
        this.invoiceService.deleteAll();
        this.stockService.deleteAll();
        this.orderService.deleteAll();
        this.productService.deleteAll();
        this.contractorService.deleteAll();
        this.requestService.deleteAll();
        this.beerService.deleteAll();
        this.contractorServiceFinance.deleteAll();
        this.productServiceFinance.deleteAll();

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
        User JOHN1 = new User("Johnny", "Dore", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "johnny.doe@gmail.com", "666664666", User.Department.PRODUCTION, "12345178900987654321123456", 99999, "09576123455");
        User JP = new User("Jan", "Pawel", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "janpawel2@gmail.com", "213721377", User.Department.FINANCE, "21370012345678901234567890", 2137, "21372137213");
        User BARBARA = new User("Baśka", "Kwarc", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "bacha@gmail.com", "666997112", User.Department.HR, "21370012345648592474567890", 3137, "690628475", User.Position.MANAGER);
        User WIESIO = new User("Wiesław", "Paleta", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "wpaleta@gmail.com", "663427112", User.Department.LOGISTICS, "21370012146824753482567890", 5137, "590427475", User.Position.MANAGER);
        User PanWIESIO = new User("Wiesław", "Puchacki", "$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6", "panwiesio@gmail.com", "667647112", User.Department.PRODUCTION, "21370012146420753482567890", 8000, "590422475", User.Position.MANAGER);
        User ANDREJ = new User("Andrej","Svoboda","$2a$10$XHOXjTseWpp9vA9NAe7unOYOQJY58bpZDcxLGn1pkNNf1QJrETfJ6","asvoboda@seznam.cz","999999999",User.Department.DISTRIBUTION,"09876543210987654321123450",1488,"91042012345");
        ADMIN.setRoles(new HashSet(Arrays.asList(Employee, Admin)));
        ADMIN1.setRoles(new HashSet(Arrays.asList(Employee, Admin)));
        JOHN.setRoles(new HashSet(Arrays.asList(Employee, Production)));
        JOHN1.setRoles(new HashSet(Arrays.asList(Employee, Production)));
        JP.setRoles(new HashSet(Arrays.asList(Employee, Finance)));
        BARBARA.setRoles(new HashSet(Arrays.asList(Employee, HR, Manager)));
        WIESIO.setRoles(new HashSet(Arrays.asList(Employee, Logistics, Manager)));
        PanWIESIO.setRoles(new HashSet(Arrays.asList(Employee, Production, Manager)));
        ANDREJ.setRoles(new HashSet(Arrays.asList(Employee, Distribution, Manager)));

        List<User> users = Arrays.asList(ADMIN, ADMIN1, JOHN, JOHN1, JP, BARBARA, WIESIO, PanWIESIO,ANDREJ);

        this.userService.saveUsers(users);

        ContractorFinance contractor1 = new ContractorFinance("JanuszeX", "gdziestam", "1234567890");
        ContractorFinance contractor2 = new ContractorFinance("HalineX", "gdzieindziej", "0987654321");
        List<ContractorFinance> contractorsFinance = Arrays.asList(contractor1, contractor2);
        this.contractorServiceFinance.saveContractors(contractorsFinance);

        Product MALTS = new Product("Malt", KG, true);
        Product HOPS = new Product("Hops", KG, true);
        Product YEAST = new Product("Yeast", KG, true);
        Product MALTS1 = new Product("Malt1", KG, true);
        Product HOPS1 = new Product("Hops1", KG, true);
        Product YEAST1 = new Product("Yeast1", KG, true);
        Product BOTTLE = new Product("Bottle", PCS);

        List<Product> products = Arrays.asList(MALTS, HOPS, YEAST, MALTS1, HOPS1, YEAST1, BOTTLE);
        this.productService.saveProducts(products);

        Stock MALT_STOCK = new Stock(MALTS, 15800.5);
        Stock HOPS_STOCK = new Stock(HOPS, 12300.5);
        Stock YEAST_STOCK = new Stock(YEAST, 56000.2);
        Stock MALT1_STOCK = new Stock(MALTS1, 158400.5);
        Stock HOPS1_STOCK = new Stock(HOPS1, 152300.5);
        Stock YEAST1_STOCK = new Stock(YEAST1, 54600.2);
        Stock BOTTLE_STOCK = new Stock(BOTTLE, 5602);

        List<Stock> stocks = Arrays.asList(MALT_STOCK, HOPS_STOCK, YEAST_STOCK, MALT1_STOCK, HOPS1_STOCK, YEAST1_STOCK, BOTTLE_STOCK);
        this.stockService.saveStocks(stocks);


        OrderItem ORDER_ITEM = new OrderItem(HOPS, 15);
        List<OrderItem> orderList = Arrays.asList(ORDER_ITEM);
        Order ORDER = new Order(orderList);

        orderService.saveOrder(ORDER);

        beerService.saveBeer(new Beer("Pilsner", new BeerIngredient(HOPS, 3.5f, BeerIngredient.IngredientType.HOP), new BeerIngredient(YEAST, 5, BeerIngredient.IngredientType.YEAST),
                new BeerIngredient(MALTS, 1800, BeerIngredient.IngredientType.MALT), new BeerIngredient(HOPS1, 4F, BeerIngredient.IngredientType.HOP), new BeerIngredient(YEAST1, 0.5F, BeerIngredient.IngredientType.YEAST)));

        Beer Ipa = new Beer("IPA");
        BeerIngredient ipaIngredient1 = new BeerIngredient(YEAST, 4.5f, BeerIngredient.IngredientType.YEAST);
        BeerIngredient ipaIngredient2 = new BeerIngredient(HOPS, 7.5f, BeerIngredient.IngredientType.HOP);
        BeerIngredient ipaIngredient3 = new BeerIngredient(MALTS, 1850, BeerIngredient.IngredientType.MALT);
        ipaIngredient1.setBeer(Ipa);
        ipaIngredient2.setBeer(Ipa);
        ipaIngredient3.setBeer(Ipa);
        Ipa.setBeerIngredients(new HashSet(Arrays.asList(ipaIngredient1, ipaIngredient2, ipaIngredient3)));
        beerService.saveBeer(Ipa);

        Contractor JANUSZEX = new Contractor(
                "JANUSZEX S.A.",
                "4593534543", "666777888",
                "ul. Brzozowa 69, 21-435 Chrząszczyrzewoszyce",
                "januszex@gmail.com",
                "777333454"
        );

        Contractor DAMIANPOL = new Contractor(
                "DamianPOL S.A.",
                "4557865443", "666555888",
                "ul. Brzozowa 21, 21-370 Chrząszczyrzewoszyce",
                "damianpol@gmail.com",
                "777333454"
        );

        List<Contractor> contractors = Arrays.asList(JANUSZEX, DAMIANPOL);
        this.contractorService.saveContractors(contractors);

        Vat vat1 = new Vat(15000);
        vat1.setStartTime(LocalDate.now());
        vat1.setProcessPhase(Vat.ProcessPhase.MALTING);
        vat1.setLastUpdated(LocalDate.now());
        vat1.setUser(JOHN);
        vat1.setBeer(Ipa);
        Vat vat2 = new Vat(10000);
        Vat vat3 = new Vat(10000);
        Vat vat4 = new Vat(5000);

        List<Vat> vats = Arrays.asList(vat1,vat2,vat3,vat4);
        vatService.saveVats(vats);



        ProductFinance product1 = new ProductFinance("Piwo", 12.3);
        ProductFinance product2 = new ProductFinance("Wino", 32.1);
        List<ProductFinance> productsFinance = Arrays.asList(product1, product2);
        this.productServiceFinance.saveProducts(productsFinance);

        InvoiceProduct invoiceProduct1 = new InvoiceProduct(product1,3.0);
        InvoiceProduct invoiceProduct2 = new InvoiceProduct(product2, 4.0);

        InvoiceProduct invoiceProduct3 = new InvoiceProduct(product1,3.0);
        InvoiceProduct invoiceProduct4 = new InvoiceProduct(product2, 4.0);

        Invoice invoice1 = new Invoice(LocalDate.now(),contractor1, Arrays.asList(invoiceProduct1, invoiceProduct2));
        Invoice invoice2 = new Invoice(LocalDate.now(),contractor2, Arrays.asList(invoiceProduct3, invoiceProduct4));
        List<Invoice> invoices = Arrays.asList(invoice1,invoice2);
        this.invoiceService.saveInvoices(invoices);
    }
}
