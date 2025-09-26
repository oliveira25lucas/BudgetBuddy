package com.oliveira25lucas.budgetBuddy.services;

import com.oliveira25lucas.budgetBuddy.models.Account;
import com.oliveira25lucas.budgetBuddy.models.User;
import com.oliveira25lucas.budgetBuddy.repositories.AccountRepository;
import com.oliveira25lucas.budgetBuddy.repositories.UserRepository;
import com.oliveira25lucas.budgetBuddy.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;
    
    public Account findById(Long id) {
        Optional<Account> account = this.accountRepository.findById(id);
        return account.orElseThrow(()-> new ObjectNotFoundException("Account not found. Id: " + id + ", Type: " + Account.class.getName()));
    }

    @Transactional
    public Account create(Account account) {
        User user = this.userService.findById(account.getUser().getId());
        account.setId(null);
        account.setUser(user);
        account = this.accountRepository.save(account);
        return account;
    }

    @Transactional
    public Account update(Account account) {
        Account newAccount = findById(account.getId());
        newAccount.setType(account.getType());
        newAccount.setStatus(account.getStatus());
        newAccount.setBalance(account.getBalance());
        newAccount.setUpdatedAt(LocalDateTime.now());
        return this.accountRepository.save(newAccount);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.accountRepository.deleteById(id);
        } catch (Exception e) {
            throw new ObjectNotFoundException("Not able to delete account. Id: " + id + ", Type: " + Account.class.getName());
        }
    }
}
