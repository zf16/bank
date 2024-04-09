package com.chuwa.bank.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(
      name = "accounts"
)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL) // The mappedBy attribute specifies the name of the field in the Transaction entity that owns the relationship
    private List<Transaction> transaction;

    @Column(columnDefinition = "numeric default 500")
    private Double balance;

}
