package com.varbro.varbro.controller.finance;

import com.varbro.varbro.service.RoleService;
import com.varbro.varbro.service.finance.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class FinanceController {

    @Autowired
    RoleService roleService;
    @Autowired
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
        addOverviewAtribbutes(model, monthStr, yearStr);
        return "finance/overview";
    }

    @PostMapping("/finance/overview")
    public String financeOverview(@RequestParam(value = "localDate", required = false) String date, Model model)
    {
        String monthStr = date.split("-")[1];
        String yearStr = date.split("-")[0];
        addOverviewAtribbutes(model, monthStr, yearStr);
        return "finance/overview";
    }

    public void addOverviewAtribbutes(Model model, String month, String year)
    {
        BigDecimal expenseSum = expenseService.getSumOfMonthlyExpenses(month, year);
        BigDecimal revenueSum = BigDecimal.valueOf(0);
        model.addAttribute("localDate",  year + "-" + month);
        model.addAttribute("monthlyExpensesSum", expenseSum);
        model.addAttribute("monthlyRevenueSum", revenueSum);
        model.addAttribute("balance", revenueSum.subtract(expenseSum));
        model.addAttribute("expenses", expenseService.getMonthlyExpenses(month, year));

    }

}
