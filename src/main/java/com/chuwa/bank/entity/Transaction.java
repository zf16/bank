package com.chuwa.bank.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
      name = "transactions"
)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id") // specifies the foreign key column name in the Transaction table that references the Account table.
    private Account account;

    @CreationTimestamp
    private LocalDateTime createdTimestamp;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "reference")
    private String reference;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "balance")
    private Double balance;

}
