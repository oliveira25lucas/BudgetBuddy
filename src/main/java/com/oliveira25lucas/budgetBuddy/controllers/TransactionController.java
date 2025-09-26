package com.oliveira25lucas.budgetBuddy.controllers;

import com.oliveira25lucas.budgetBuddy.models.Account;
import com.oliveira25lucas.budgetBuddy.models.Transaction;
import com.oliveira25lucas.budgetBuddy.models.User;
import com.oliveira25lucas.budgetBuddy.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        return ResponseEntity.ok().body(transaction);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transaction) {
        transactionService.create(transaction);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(transaction.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@Valid @RequestBody Transaction transaction, @PathVariable Long id) {
        transaction.setId(id);
        transactionService.update(transaction);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
