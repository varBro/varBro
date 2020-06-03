package com.varbro.varbro.controller.finance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExpenseController {

    @GetMapping("/finance/add-expense")
    public String addExpense() {
        return "finance/add-expense";
    }
}
