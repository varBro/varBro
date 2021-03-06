package com.varbro.varbro.controller.logistics;


import com.varbro.varbro.model.distribution.BeerStock;
import com.varbro.varbro.model.logistics.*;

import com.varbro.varbro.model.production.BeerIngredient;
import com.varbro.varbro.model.production.Request;
import com.varbro.varbro.service.RoleService;
import com.varbro.varbro.service.logistics.ContractorService;
import com.varbro.varbro.service.logistics.OrderService;
import com.varbro.varbro.service.logistics.ProductService;
import com.varbro.varbro.service.logistics.StockService;
import com.varbro.varbro.service.production.RequestService;
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
import java.util.*;


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

    @Autowired
    RequestService requestService;

    @ModelAttribute
    public Order order() {
        Order order = new Order();
        return order;
    }

    @GetMapping("/logistics")
    public String logisticsIndex() {
        return "logistics/index";
    }

    @GetMapping("/logistics/stock")
    public String currentStock(Model model)
    {
        model.addAttribute("ingredientStocks", stockService.getIngredientStocks());
        model.addAttribute("notIngredientStocks", stockService.getNotIngredientStocks());
        return "logistics/stock/show";
    }

    @GetMapping("/logistics/stock/edit")
    public String editStockForm(Model model) {
        model.addAttribute("productStocks", stockService.getStocks());
        model.addAttribute("stock", new Stock());
        model.addAttribute("operation", "SUBSTITUTE");
        return "logistics/stock/edit";
    }

    @PostMapping("/logistics/stock/edit")
    public String editStock(@RequestParam(value = "operation") String operation, @ModelAttribute Stock stock) {
        if(operation.equals("ADD")) {
            stockService.addToStock(stock.getProduct().getId(), stock.getQuantity());
        }
        else if(operation.equals("SUBSTITUTE")) {
            stockService.substituteFromStock(stock.getProduct().getId(), stock.getQuantity());
        }
        return "redirect:/logistics/stock";
    }

    @GetMapping("/logistics/new-order")
    public String newOrder(@ModelAttribute Order order, Model model)
    {
        List<Product> products =  productService.getProducts();
        model.addAttribute("products", products);
        return "logistics/new-order";
    }

    @GetMapping("/logistics/manager/contractors")
    public String contractors(Model model) {
        model.addAttribute("contractors", contractorService.getContractors());
        return "logistics/manager/contractors";
    }

    @GetMapping("/logistics/new-order/request/{id}")
    public String orderFromRequest(@PathVariable("id") long id, @ModelAttribute Order order, Model model) {
        order.getOrderItems().remove(0);
        Request request = requestService.getRequestById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
        double multiplier = request.getAmount() / 1000.0;
        for (BeerIngredient ingredient: request.getBeer().getBeerIngredients()) {
            double quantity = ingredient.getQuantity() * multiplier;
            order.getOrderItems().add(new OrderItem(ingredient.getIngredient(), quantity));
        }
        order.getOrderItems().add(new OrderItem(productService.getProductByName("Bottle"), request.getAmount() * 2));
        List<Product> products =  productService.getProducts();
        model.addAttribute("request_id", request.getId());
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
    public ModelAndView saveOrder(@Valid @ModelAttribute Order order, @RequestParam(value = "request_id", required = false) Long request_id,
                                  SessionStatus status, BindingResult bindingResult)
    {
        if(!bindingResult.hasErrors()) {
            List<OrderItem> actualOrder = new ArrayList<>();
            for (OrderItem orderItem : order.getOrderItems()) {
                Product p = productService.getProductById(orderItem.getProduct().getId());
                if (p != null & orderItem.getQuantity() > 0) {
                    actualOrder.add(new OrderItem(p, orderItem.getQuantity()));
                }
            }
            Order newOrder = new Order(actualOrder);
            if(request_id != null) {
                Request request = requestService.getRequestById(request_id)
                        .orElseThrow(() -> new IllegalArgumentException("No request by id: " + request_id));
                newOrder.setRequest(request);
                request.setStatus(Request.Status.ORDERED);
                requestService.save(request);
            }
            orderService.saveOrder(newOrder);
            status.setComplete();

            return new ModelAndView("redirect:/logistics/order/" + newOrder.getId());
        }
        return new ModelAndView("redirect:/logistics/new-order");
    }

    @GetMapping("/logistics/manager/order-history")
    public String orderHistory(Model model)
    {
        int month = LocalDate.now().getMonthValue();
        String monthStr = month < 10 ? "0" + month : String.valueOf(month);
        String yearStr = String.valueOf(LocalDate.now().getYear());
        model.addAttribute("orders", orderService.getMonthlyOrdersApproved(monthStr, yearStr));
        model.addAttribute("localDate",  yearStr + "-" + monthStr);
        return "logistics/manager/order-history";
    }

    @PostMapping("/logistics/manager/order-history")
    public String orderHistory(@RequestParam(value = "localDate", required = false) String date, Model model)
    {
        String monthStr = date.split("-")[1];
        String yearStr = date.split("-")[0];
        model.addAttribute("orders", orderService.getMonthlyOrdersApproved(monthStr, yearStr));
        model.addAttribute("localDate",  yearStr + "-" + monthStr);
        return "logistics/manager/order-history";
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
        if(order.getRequest() != null) {
            Request request = requestService.getRequestById(order.getRequest().getId())
                    .orElseThrow(() -> new IllegalArgumentException("No request by id: " + order.getRequest().getId()));
            requestService.requestReady(request);
        }
        stockService.updateStocksAdd(order);
        orderService.saveOrder(order);
        requestService.updateRequestsAvailability();
        return "redirect:/logistics/current-orders";
    }

    @GetMapping("/logistics/order/{id}")
    public String showOrder(@PathVariable("id") long id, Model model) {
        Order order = orderService.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));

        model.addAttribute("orderView", order);
        return "logistics/order";
    }

    @GetMapping("/logistics/order/{id}/approve")
    public String showOrderApprove(@PathVariable("id") long id, Model model) {
        Order order = orderService.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("contractors", contractorService.getContractors());
        model.addAttribute("approved", order);
        return "logistics/order-approve";
    }

    @GetMapping("/logistics/order/{id}/reject")
    public String rejectOrder(@PathVariable("id") long id, Model model) {
        Order order = orderService.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        if(order.getRequest() != null) {
            Request request = requestService.getRequestById(order.getRequest().getId())
                    .orElseThrow(() -> new IllegalArgumentException("No request by id: " + order.getRequest().getId()));
            request.setStatus(Request.Status.PENDING);
            requestService.save(request);
        }
        orderService.delete(order.getId());
        model.addAttribute("orders", orderService.getOrdersForApproval());
        return "logistics/manager/for-approval";
    }

    @PostMapping("/logistics/order/{id}/approve")
    public String approveOrder(@PathVariable("id") long id, @ModelAttribute Order approved)
    {
        if(!approved.getContractor().getName().equals(""))
        {
            Order orderToUpdate = orderService.getOrderById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
            Contractor contractor = contractorService.getContractorByName(approved.getContractor().getName());
            orderToUpdate.setContractor(contractor);
            orderToUpdate.setOrderStatus(Order.Status.APPROVED);
            orderToUpdate.setOrderStatus(Order.Status.IN_PROGRESS);
            orderService.saveOrder(orderToUpdate);
            return "redirect:/logistics/order/" + id;
        }
        return "redirect:/logistics/order/" + id + "/approve";
    }

    @GetMapping("/logistics/manager/for-approval")
    public String ordersForApproval(Model model)
    {
        model.addAttribute("orders", orderService.getOrdersForApproval());
        return "logistics/manager/for-approval";
    }
}

