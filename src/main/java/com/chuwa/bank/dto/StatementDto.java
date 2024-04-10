package com.chuwa.bank.dto;

import java.time.LocalDate;
import java.util.List;

public class StatementDto {
    private String name;

    private String address;

    private String accountNo;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<StatementTransactionDto> transactions;

    private double totalWithdrawal;

    private double totalDeposit;

    public StatementDto() {

    }

    public StatementDto(String name, String address, String accountNo, LocalDate startDate, LocalDate endDate, List<StatementTransactionDto> transactions, double totalWithdrawal, double totalDeposit) {
        this.name = name;
        this.address = address;
        this.accountNo = accountNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.transactions = transactions;
        this.totalWithdrawal = totalWithdrawal;
        this.totalDeposit = totalDeposit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<StatementTransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<StatementTransactionDto> transactions) {
        this.transactions = transactions;
    }

    public double getTotalWithdrawal() {
        return totalWithdrawal;
    }

    public void setTotalWithdrawal(double totalWithdrawal) {
        this.totalWithdrawal = totalWithdrawal;
    }

    public double getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(double totalDeposit) {
        this.totalDeposit = totalDeposit;
    }
}
