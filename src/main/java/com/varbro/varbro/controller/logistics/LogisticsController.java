package com.varbro.varbro.controller.logistics;

import com.varbro.varbro.model.User;
import com.varbro.varbro.model.logistics.Order;
import com.varbro.varbro.model.logistics.OrderItem;
import com.varbro.varbro.model.logistics.Product;
import com.varbro.varbro.service.RoleService;
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
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
    public String saveOrder(@Valid @ModelAttribute Order order, SessionStatus status, BindingResult bindingResult)
    {
        if(!bindingResult.hasErrors()) {
            List<OrderItem> actualOrder = new ArrayList<>();
            for (OrderItem orderItem : order.getOrderItems()) {
                Product p = productService.getProductByName(orderItem.getProduct().getName());
                if (p != null) {
                    actualOrder.add(new OrderItem(p, orderItem.getQuantity()));
                }
            }
            orderService.saveOrder(new Order(actualOrder));
            status.setComplete();
        }
        return "redirect:/default";
    }

    @GetMapping("/logistics/order-history")
    public String orderHistory(Model model)
    {
        int month = LocalDate.now().getMonthValue();
        String monthStr = month < 10 ? "0" + month : String.valueOf(month);
        String yearStr = String.valueOf(LocalDate.now().getYear());
        model.addAttribute("orders", orderService.getMonthlyOrdersApproved(monthStr, yearStr));
        model.addAttribute("localDate",  yearStr + "-" + monthStr);
        return "logistics/order-history";
    }

    @PostMapping("/logistics/order-history")
    public String orderHistory(@RequestParam(value = "localDate", required = false) String date, Model model)
    {
        String monthStr = date.split("-")[1];
        String yearStr = date.split("-")[0];
        model.addAttribute("orders", orderService.getMonthlyOrdersApproved(monthStr, yearStr));
        model.addAttribute("localDate",  yearStr + "-" + monthStr);
        return "logistics/order-history";
    }

    @GetMapping("/logistics/current-orders")
    public String currentOrders(Model model)
    {
        model.addAttribute("orders", orderService.getInProgressOrders());
        return "logistics/current-orders";
    }


    @PostMapping("/logistics/order/{id}/arrived")
    public String orderArrived(@PathVariable("id") long id)
    {
        Order order = orderService.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        order.setOrderStatus(Order.Status.RECEIVED);
        orderService.saveOrder(order);
        return "redirect:/logistics/current-orders";
    }

    @GetMapping("/logistics/order/{id}")
    public String showOrder(@PathVariable("id") long id, Model model) {
        Order order = orderService.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));

        model.addAttribute("order", order);
        return "logistics/order";
    }
}

