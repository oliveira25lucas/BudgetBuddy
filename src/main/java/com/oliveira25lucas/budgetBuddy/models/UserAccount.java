package com.oliveira25lucas.budgetBuddy.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_account")
@Getter
@Setter
public class UserAccount {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @Column(unique = true)
    @NotBlank
    @Size(min = 3, max = 80)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column
    @NotBlank
    @Size(min = 8, max = 50)
    private String password;

    @Column
    @NotBlank
    @Size(min = 3, max = 50)
    private String phone;

    @Column
    @NotBlank
    @Size(min = 3, max = 100)
    private String address;

    @Column
    @NotBlank
    private LocalDateTime createdAt;

    @Column
    @NotBlank
    private LocalDateTime updatedAt;

    public UserAccount() {
    }

    public UserAccount(long id, String name, String email, String password, String phone, String address, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserAccount userAccount)) return false;
        return getId() == userAccount.getId() && Objects.equals(getName(), userAccount.getName()) && Objects.equals(getEmail(), userAccount.getEmail()) && Objects.equals(getPassword(), userAccount.getPassword()) && Objects.equals(getPhone(), userAccount.getPhone()) && Objects.equals(getAddress(), userAccount.getAddress()) && Objects.equals(getCreatedAt(), userAccount.getCreatedAt()) && Objects.equals(getUpdatedAt(), userAccount.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getPassword(), getPhone(), getAddress(), getCreatedAt(), getUpdatedAt());
    }
}
