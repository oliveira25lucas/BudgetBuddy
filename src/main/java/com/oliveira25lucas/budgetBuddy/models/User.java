package com.oliveira25lucas.budgetBuddy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_info",
        uniqueConstraints = @UniqueConstraint(name = "uq_user_email", columnNames = "email"))
@Getter @Setter @NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 2, max = 100)
    private String name;

    @Past
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotBlank @Size(min = 9, max = 15)
    private String cpf;

    @Email @NotBlank @Size(max = 150)
    @Column(nullable = false)
    private String email;

    @NotBlank @Size(min = 8, max = 255)
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 50)
    private String phone;

    @Size(max = 200)
    private String address;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void touch() { this.updatedAt = LocalDateTime.now(); }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User other = (User) o;
        return id != null && id.equals(other.id);
    }
    @Override public int hashCode() { return 31; }
}
