package com.oliveira25lucas.budgetBuddy.repositories;

import com.oliveira25lucas.budgetBuddy.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUser_Id(Long id);

}