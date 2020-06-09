package com.varbro.varbro.service.finance;

import com.varbro.varbro.model.finance.Expense;
import com.varbro.varbro.repository.finance.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    public void saveExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public Iterable<Expense> getExpenses() {
        return expenseRepository.findAll();
    }
}