package com.varbro.varbro.controller.finance;

import com.varbro.varbro.model.finance.Expense;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExpenseController {

    @GetMapping("/finance/add-expense")
    public String addExpense(Model model) {
        model.addAttribute("expense", new Expense());
        return "finance/add-expense";
    }
}
