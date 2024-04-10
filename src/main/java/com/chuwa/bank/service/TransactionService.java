package com.chuwa.bank.service;

import com.chuwa.bank.dto.TransactionDto;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    TransactionDto createTransaction(long accountId, TransactionDto transactionDto);

    TransactionDto getTransactionById(long transactionId);

    List<TransactionDto> getAllTransactions(long accountId);

    List<TransactionDto> getAllTransactionsBetween(long accountId, LocalDate startDate, LocalDate endDate);

    void deleteTransaction(long transactionId);

}
