package com.oliveira25lucas.budgetBuddy.services;

import com.oliveira25lucas.budgetBuddy.models.Account;
import com.oliveira25lucas.budgetBuddy.models.Transaction;
import com.oliveira25lucas.budgetBuddy.models.User;
import com.oliveira25lucas.budgetBuddy.repositories.TransactionRepository;
import com.oliveira25lucas.budgetBuddy.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @Transactional
    public Transaction findById(Long id) {
        Optional<Transaction> transaction = this.transactionRepository.findById(id);
        return transaction.orElseThrow(() -> new ObjectNotFoundException("Transaction not found. Id: " + id + ", Type: " + Transaction.class.getName()));
    }

    @Transactional
    public Transaction create(Transaction transaction) {
        Account account = this.accountService.findById(transaction.getAccount().getId());
        transaction.setId(null);
        transaction.setAccount(account);
        return this.transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction update(Transaction transaction) {
        Transaction transactionToUpdate = this.findById(transaction.getId());
        transactionToUpdate.setAmount(transaction.getAmount());
        transactionToUpdate.setTransactionDate(transaction.getTransactionDate());
        transactionToUpdate.setType(transaction.getType());
        transactionToUpdate.setDescription(transaction.getDescription());
        return this.transactionRepository.save(transactionToUpdate);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.transactionRepository.deleteById(id);
        } catch (Exception e) {
            throw new ObjectNotFoundException("Not able to delete Transaction. Id: " + id + ", Type: " + Transaction.class.getName());
        }
    }
}
