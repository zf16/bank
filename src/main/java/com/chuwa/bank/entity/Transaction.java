package com.chuwa.bank.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
      name = "transactions"
)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id") // specifies the foreign key column name in the Transaction table that references the Account table.
    private Account account;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "reference")
    private long reference;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @CreationTimestamp
    private LocalDateTime createdTimestamp;

    public Transaction() {

    }

    public Transaction(Account account, String description, long reference, Double amount, Double balance, LocalDate transactionDate) {
        this.account = account;
        this.description = description;
        this.reference = reference;
        this.amount = amount;
        this.balance = balance;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getReference() {
        return reference;
    }

    public void setReference(long reference) {
        this.reference = reference;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
