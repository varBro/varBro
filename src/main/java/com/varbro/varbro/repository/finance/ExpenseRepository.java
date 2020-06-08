package com.varbro.varbro.repository.finance;

import com.varbro.varbro.model.finance.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query(value = "select sum(amount) from expense e where extract(month from e.date) = ?1 " +
            "and extract(year from e.date) = ?2", nativeQuery = true)
    BigDecimal sumOfMonthlyExpenses(String month, String year);

    @Query(value = "select * from expense e where extract(month from e.date) = ?1 and extract(year from e.date) = ?2",
            nativeQuery=true)
    List<Expense> monthlyExpenses(String month, String year);
}
