package com.chuwa.bank.controller;

import com.chuwa.bank.dto.TransactionDto;
import com.chuwa.bank.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ap1/v1/transactions/")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping( "{accountId}")
    public List<TransactionDto> getAllTransactions(@PathVariable(name = "accountId") long accountId) {
        return null;
    }

    @GetMapping("{accountId}/{transactionId}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable(name = "accountId") long accountId,
                                                             @PathVariable(name = "transactionId") long transactionId) {
        return null;
    }
}
