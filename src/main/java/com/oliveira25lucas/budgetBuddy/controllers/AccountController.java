package com.oliveira25lucas.budgetBuddy.controllers;

import com.oliveira25lucas.budgetBuddy.models.Account;
import com.oliveira25lucas.budgetBuddy.services.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/account")
@Validated
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<Account> findAccountbyId(@PathVariable Long id) {
        Account account = accountService.findById(id);
        return ResponseEntity.ok().body(account);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
        accountService.create(account);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> updateAccount(@Valid @RequestBody Account account, @PathVariable Long id){
        account.setId(id);
        accountService.update(account);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable Long id){
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
