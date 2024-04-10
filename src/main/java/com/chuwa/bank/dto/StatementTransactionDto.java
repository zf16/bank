package com.chuwa.bank.dto;

import lombok.Builder;

public class StatementTransactionDto {

    private long id;

    private String transactionDate;

    private String description;

    private long reference;

    private double amount;

    private double balance;

    public StatementTransactionDto() {

    }

    public StatementTransactionDto(long id, String transactionDate, String description, long reference, double amount, double balance) {
        this.id = id;
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

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
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
