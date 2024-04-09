package com.chuwa.bank.dto;

import com.chuwa.bank.entity.Account;

import java.time.LocalDateTime;

public class TransactionDto {

    private long id;

    private Account account;

    private LocalDateTime createdTimestamp;

    private String description;

    private String reference;

    private double amount;

    private double balance;
}
