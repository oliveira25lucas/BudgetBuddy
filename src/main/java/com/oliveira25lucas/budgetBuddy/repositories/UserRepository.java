package com.oliveira25lucas.budgetBuddy.repositories;


import com.oliveira25lucas.budgetBuddy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
