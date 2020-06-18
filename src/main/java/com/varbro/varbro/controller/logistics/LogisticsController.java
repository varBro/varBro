package com.varbro.varbro.controller.logistics;

import com.varbro.varbro.service.RoleService;
import com.varbro.varbro.service.logistics.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogisticsController {

    @Autowired
    StockService stockService;

    @Autowired
    RoleService roleService;

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

}

