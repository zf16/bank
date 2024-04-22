package com.chuwa.bank.service.impl;

import com.chuwa.bank.dao.AccountRepository;
import com.chuwa.bank.dao.TransactionRepository;
import com.chuwa.bank.dto.TransactionDto;
import com.chuwa.bank.entity.Account;
import com.chuwa.bank.entity.Transaction;
import com.chuwa.bank.exception.ResourceNotFoundException;
import com.chuwa.bank.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public TransactionDto createTransaction(long accountId, TransactionDto transactionDto) {

        // retrieve account by accountId
        Account account = accountRepository.findById(accountId)
              .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));

        // update account balance
        double newBalance = account.getBalance() + transactionDto.getAmount();
        account.setBalance(newBalance);

        // save account entity to DB
        accountRepository.save(account);
        log.info("Account id {} has updated its balance in DB.", account.getId());

        // create transaction entity
        Transaction transaction = mapToEntity(transactionDto);
        transaction.setAccount(account);
        transaction.setBalance(account.getBalance());

        // save transaction entity to DB
        Transaction savedTransaction = transactionRepository.save(transaction);

        log.info("Transaction with id {} is committed.", savedTransaction.getId());

        return mapToDto(savedTransaction);
    }

    @Override
    public List<TransactionDto> getAllTransactions(long accountId) {

        // retrieve account by accountId
        Account account = accountRepository.findById(accountId)
              .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));

        // retrieve all transactions by this account
        return transactionRepository.findAll().stream()
              .filter(t -> t.getAccount().getId().equals(account.getId()))
              .map(this::mapToDto)
              .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> getAllTransactionsBetween(long accountId, LocalDate startDate, LocalDate endDate) {

        // retrieve account by accountId
        Account account = accountRepository.findById(accountId)
              .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));

        return transactionRepository.findTransactionByTransactionDateBetween(startDate, endDate).stream()
              .filter(t -> t.getAccount().getId().equals(account.getId()))
              .map(this::mapToDto)
              .collect(Collectors.toList());

    }

    @Override
    public TransactionDto getTransactionById(long transactionId) {

        Transaction transaction = transactionRepository.findById(transactionId)
              .orElseThrow(() -> new ResourceNotFoundException("Transaction", "Id", transactionId));

        return mapToDto(transaction);
    }

    @Deprecated
    @Override
    public void deleteTransaction(long transactionId) {

        // retrieve transaction by transactionId
        Transaction transaction = transactionRepository.findById(transactionId)
              .orElseThrow(() -> new ResourceNotFoundException("Transaction", "Id", transactionId));

        // retrieve account by accountId
        Account account = accountRepository.findById(transaction.getAccount().getId())
              .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", transaction.getAccount().getId()));

        double newBalance = account.getBalance() - transaction.getBalance();
        account.setBalance(newBalance);
    }

    private TransactionDto mapToDto(Transaction transaction) {

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setDescription(transaction.getDescription());
        transactionDto.setReference(transaction.getReference());
        transactionDto.setTransactionDate(transaction.getTransactionDate());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setAccount(transaction.getAccount());
        transactionDto.setBalance(transaction.getBalance());

        return transactionDto;
    }

    private Transaction mapToEntity(TransactionDto transactionDto) {

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(transactionDto.getTransactionDate());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setReference(transactionDto.getReference());
        transaction.setAmount(transactionDto.getAmount());

        return transaction;
    }
}
