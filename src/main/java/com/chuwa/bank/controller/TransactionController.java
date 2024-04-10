package com.chuwa.bank.controller;

import com.chuwa.bank.dto.TransactionDto;
import com.chuwa.bank.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("ap1/v1/transactions/")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("{accountId}")
    public ResponseEntity<TransactionDto> createTransaction(@PathVariable(name = "accountId") long accountId,
                                                            @RequestBody TransactionDto transactionDto) {

        return new ResponseEntity<>(transactionService.createTransaction(accountId, transactionDto), HttpStatus.CREATED);
    }


    @GetMapping( "/{accountId}/all")
    public List<TransactionDto> getAllTransactions(@PathVariable(name = "accountId") long accountId) {
        return transactionService.getAllTransactions(accountId);
    }

    @GetMapping( "/{accountId}/between")
    public List<TransactionDto> getAllTransactions(@PathVariable(name = "accountId") long accountId,
                                                   @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                   @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return transactionService.getAllTransactionsBetween(accountId, startDate, endDate);
    }

    @GetMapping("{transactionId}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable(name = "transactionId") long transactionId) {
        return new ResponseEntity<>(transactionService.getTransactionById(transactionId), HttpStatus.OK);
    }

    @Deprecated
    @DeleteMapping("{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable(name = "transactionId") long transactionId) {
        transactionService.deleteTransaction(transactionId);
        return new ResponseEntity<>("Transaction deleted successfully", HttpStatus.OK);
    }
}
