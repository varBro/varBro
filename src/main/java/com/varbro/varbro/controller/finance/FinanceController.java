package com.varbro.varbro.controller.finance;

import com.varbro.varbro.service.RoleService;
import com.varbro.varbro.service.finance.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class FinanceController {

    @Autowired
    RoleService roleService;
    ExpenseService expenseService;

    @GetMapping("/finance")
    public String financeIndex() {
        return "finance/index";
    }

    @GetMapping("/finance/overview")
    public String financeOverview(Model model)
    {
        int month = LocalDate.now().getMonthValue();
        String monthStr = month < 10 ? "0" + month : String.valueOf(month);
        String yearStr = String.valueOf(LocalDate.now().getYear());
        model.addAttribute("localDate",  yearStr+ "-" + monthStr);
        if(monthStr != null && yearStr != null) {
            model.addAttribute("monthlyExpensesSum", expenseService.getSumOfMonthlyExpenses(monthStr, yearStr));
            model.addAttribute("expenses", expenseService.getMonthlyExpenses(monthStr, yearStr));
        }
        return "finance/overview";
    }
}
