package com.chuwa.bank.service;

import com.chuwa.bank.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto getTransactionById(long accountId, long transactionId);
    List<TransactionDto> getAllTransactions(long accountId);
}
