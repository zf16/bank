package com.chuwa.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public class StatementDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("accountNo")
    private String accountNo;

    @JsonProperty("startDate")
    private LocalDate startDate;

    @JsonProperty("endDate")
    private LocalDate endDate;

    @JsonProperty("transactions")
    private List<StatementTransactionDto> transactions;

    @JsonProperty("totalWithdrawal")
    private double totalWithdrawal;

    @JsonProperty("totalDeposit")
    private double totalDeposit;

}
