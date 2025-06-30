package com.bms.bank_management_system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name = "account_number", length = 20, nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "pin", length = 100, nullable = false)  // store encoded pin (password)
    private String pin;

    @Column(name = "cvv", length = 5, nullable = false)
    private String cvv;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "account_number"))
    private List<String> roles;

    // 👇 Required for Spring Security JWT
    public String getUsername() {
        return accountNumber;
    }

    public String getPassword() {
        return pin;
    }

    public List<String> getRoles() {
        return roles;
    }
}
