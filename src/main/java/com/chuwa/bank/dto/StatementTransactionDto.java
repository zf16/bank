package com.chuwa.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class StatementTransactionDto {

    @JsonProperty("id")
    private long id;

    @JsonProperty("transactionDate")
    private String transactionDate;

    @JsonProperty("description")
    private String description;

    @JsonProperty("reference")
    private long reference;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("balance")
    private double balance;

}
