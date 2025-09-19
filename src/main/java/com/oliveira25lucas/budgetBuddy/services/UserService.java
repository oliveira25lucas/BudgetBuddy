package com.oliveira25lucas.budgetBuddy.services;

import com.oliveira25lucas.budgetBuddy.models.User;
import com.oliveira25lucas.budgetBuddy.repositories.AccountRepository;
import com.oliveira25lucas.budgetBuddy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(()-> new RuntimeException("User not found. Id: " + id + ", Type: " + User.class.getName()));
    }

    @Transactional
    public User create(User user) {
        user.setId(null);
        user = this.userRepository.save(user);
        return user;
    }

    @Transactional
    public User update(User user) {
        User newUser = findById(user.getId());
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPhone(user.getPhone());
        newUser.setAddress(user.getAddress());
        newUser.setUpdatedAt(LocalDateTime.now());
        return this.userRepository.save(newUser);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Not able to delete user. Id: " + id + ", Type: " + User.class.getName());
        }
    }
}
