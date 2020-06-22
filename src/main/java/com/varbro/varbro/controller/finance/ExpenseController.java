package com.varbro.varbro.controller.finance;

import com.varbro.varbro.model.finance.Expense;
import com.varbro.varbro.service.finance.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping("/finance/add-expense")
    public String financeAddExpense(Model model) {
        model.addAttribute("expense", new Expense());
        return "finance/add-expense";
    }

    @PostMapping("/finance/add-expense")
    public String financeAddExpenseSubmit(@Valid @ModelAttribute("expense") Expense expense, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            expenseService.saveExpense(expense);
        }
        return "finance/index";
    }
}
