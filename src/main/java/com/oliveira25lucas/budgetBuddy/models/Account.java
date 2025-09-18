package com.oliveira25lucas.budgetBuddy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account",
        uniqueConstraints = @UniqueConstraint(name = "uq_account_user_name", columnNames = {"user_id","name"}))
@Getter @Setter @NoArgsConstructor
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_account_user"))
    private User user;


    @NotNull
    @Column(nullable = false, length = 20)
    private String type;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AccountStatus status = AccountStatus.ACTIVE;

    @NotNull
    @Column(nullable = false)
    private Integer accountNumber;

    @PositiveOrZero
    @Column(nullable = false)
    private double balance = 0.0;

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account other = (Account) o;
        return id != null && id.equals(other.id);
    }
    @Override public int hashCode() { return 31; }

    public enum AccountStatus { ACTIVE, ARCHIVED }
}
