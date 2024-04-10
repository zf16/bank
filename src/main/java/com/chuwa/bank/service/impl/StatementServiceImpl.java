package com.chuwa.bank.service.impl;

import com.chuwa.bank.dto.AccountDto;
import com.chuwa.bank.dto.StatementDto;
import com.chuwa.bank.dto.StatementTransactionDto;
import com.chuwa.bank.dto.TransactionDto;
import com.chuwa.bank.service.AccountService;
import com.chuwa.bank.service.StatementService;
import com.chuwa.bank.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatementServiceImpl implements StatementService {

    private final TransactionService transactionService;
    private final AccountService accountService;

    public StatementServiceImpl(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @Override
    public StatementDto getStatement(long accountId, LocalDate startDate, LocalDate endDate) {

        AccountDto accountDto = accountService.getAccountById(accountId);

        List<TransactionDto> transactions = transactionService.getAllTransactionsBetween(accountId, startDate, endDate);

        double totalWithdrawal = transactions.stream()
              .filter(t -> t.getAmount() < 0)
              .mapToDouble(TransactionDto::getAmount)
              .sum();

        double totalDeposit = transactions.stream()
              .filter(t -> t.getAmount() >= 0)
              .mapToDouble(TransactionDto::getAmount)
              .sum();

        List<StatementTransactionDto> statementTransactions = transactions.stream()
              .map(this::mapToStatementTransactionDto)
              .toList();

        return StatementDto.builder()
              .name(accountDto.getName())
              .accountNo(String.valueOf(accountDto.getId()))
              .address(accountDto.getAddress())
              .transactions(statementTransactions)
              .startDate(startDate)
              .endDate(endDate)
              .totalWithdrawal(totalWithdrawal)
              .totalDeposit(totalDeposit)
              .build();
    }

    private StatementTransactionDto mapToStatementTransactionDto(TransactionDto transactionDto) {

        return StatementTransactionDto.builder()
              .id(transactionDto.getId())
              .transactionDate(transactionDto.getTransactionDate().toString())
              .description(transactionDto.getDescription())
              .reference(transactionDto.getReference())
              .amount(transactionDto.getAmount())
              .balance(transactionDto.getBalance())
              .build();

    }
}
