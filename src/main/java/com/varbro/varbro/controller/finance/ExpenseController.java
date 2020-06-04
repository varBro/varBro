package com.varbro.varbro.controller.finance;

import com.varbro.varbro.model.finance.Expense;
import com.varbro.varbro.service.finance.ExpenseService;
import org.hibernate.pretty.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String financeAddExpenseSubmit(@ModelAttribute Expense expense, BindingResult bindingResult) {
        expenseService.saveExpense(expense);
        if (bindingResult.hasErrors()) {
            return "finance/add-expense";
        }
        return "finance/add-expense";
    }
}
