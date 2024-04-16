package com.chuwa.bank.dto;

import com.chuwa.bank.entity.Account;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TransactionDto {

    private long id;

    private Account account;

    private LocalDate transactionDate;

    @NotEmpty
    @Size(min = 2, message = "Description must be at least 2 characters.")
    private String description;

    private long reference;

    private double amount;

    private double balance;

    public TransactionDto() {

    }

    public TransactionDto(long id, Account account, LocalDate transactionDate, String description, long reference, double amount, double balance) {
        this.id = id;
        this.account = account;
        this.transactionDate = transactionDate;
        this.description = description;
        this.reference = reference;
        this.amount = amount;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
