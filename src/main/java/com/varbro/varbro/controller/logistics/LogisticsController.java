package com.varbro.varbro.controller.logistics;

import com.varbro.varbro.model.logistics.Order;
import com.varbro.varbro.model.logistics.OrderItem;
import com.varbro.varbro.model.logistics.Product;
import com.varbro.varbro.service.RoleService;
import com.varbro.varbro.service.logistics.ContractorService;
import com.varbro.varbro.service.logistics.OrderService;
import com.varbro.varbro.service.logistics.ProductService;
import com.varbro.varbro.service.logistics.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("order")
public class LogisticsController {

    @Autowired
    StockService stockService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    RoleService roleService;

    @Autowired
    ContractorService contractorService;

    @ModelAttribute
    public Order order() {
        Order order = new Order();
        System.out.println("The size of the order at start is: " + order.getOrderItems().size());
        return order;
    }

    @GetMapping("/logistics")
    public String logisticsIndex() {
        return "logistics/index";
    }

    @GetMapping("/logistics/stock")
    public String currentStock(Model model)
    {
        model.addAttribute("stocks", stockService.getStocks());
        return "logistics/stock";
    }

    @GetMapping("/logistics/new-order")
    public String newOrder(@ModelAttribute Order order, Model model)
    {
        List<Product> products =  productService.getProducts();
        model.addAttribute("products", products);
        return "logistics/new-order";
    }

    @GetMapping("/logistics/contractors")
    public String contractors(Model model) {
        model.addAttribute("contractors", contractorService.getContractors());
        return "logistics/contractors";
    }

    @RequestMapping(value = "/logistics/new-order", params = "addRow")
    public String addRow(@ModelAttribute Order order, Model model)
    {
        order.getOrderItems().add(new OrderItem());
        List<Product> products =  productService.getProducts();
        model.addAttribute("products", products);
        return "logistics/new-order";
    }

    @RequestMapping(value = "/logistics/new-order", params = "removeRow")
    public String removeRow(@ModelAttribute Order order, Model model, HttpServletRequest req)
    {
        Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        order.getOrderItems().remove(rowId.intValue());
        List<Product> products =  productService.getProducts();
        model.addAttribute("products", products);
        return "logistics/new-order";
    }

    @RequestMapping(value = "/logistics/new-order", params = "submit")
    public String saveOrder(@ModelAttribute Order order, SessionStatus status)
    {
        List<OrderItem> actualOrder = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            Product p = productService.getProductByName(orderItem.getProduct().getName());
            if (p != null) {
                actualOrder.add(new OrderItem(p, orderItem.getQuantity()));
            }
        }
        orderService.saveOrder(new Order(actualOrder));
        status.setComplete();
        return "redirect:/default";
    }




}

