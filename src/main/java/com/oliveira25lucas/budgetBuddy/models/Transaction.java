package com.oliveira25lucas.budgetBuddy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transaction",
        uniqueConstraints = @UniqueConstraint(name = "uq_transaction_user_name", columnNames = {"account_id","name"}))
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Account account;

    @NotNull
    @Column(precision = 19, scale = 2)
    private BigDecimal amount = BigDecimal.ZERO;

    @NotNull
    @DateTimeFormat
    @Column
    private LocalDate transactionDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    private Type type =  Type.INCOME;

    @Column
    private String description;


    public enum Type { INCOME, EXPANSE};

}
