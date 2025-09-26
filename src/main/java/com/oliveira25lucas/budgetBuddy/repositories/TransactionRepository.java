package com.oliveira25lucas.budgetBuddy.repositories;

import com.oliveira25lucas.budgetBuddy.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
