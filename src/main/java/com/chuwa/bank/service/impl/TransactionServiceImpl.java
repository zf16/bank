package com.chuwa.bank.service.impl;

import com.chuwa.bank.dto.TransactionDto;
import com.chuwa.bank.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    public TransactionDto getTransactionById(long accountId, long transactionId) {
        return null;
    }

    @Override
    public List<TransactionDto> getAllTransactions(long accountId) {
        return null;
    }
}
