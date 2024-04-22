package com.chuwa.bank.service.impl;

import com.chuwa.bank.dao.AccountRepository;
import com.chuwa.bank.dao.TransactionRepository;
import com.chuwa.bank.dto.TransactionDto;
import com.chuwa.bank.entity.Account;
import com.chuwa.bank.entity.Transaction;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl sut;

    @Mock
    private TransactionRepository transactionRepositoryMock;

    @Mock
    private AccountRepository accountRepositoryMock;

    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    public void beforeEach() {

    }

    @AfterAll
    static void afterAll() {

    }

    @AfterEach
    public void afterEach() {

    }

    @Test
    void createTransaction() {
        // GIVEN
        long accountId = 9999;
        long transactionId = 1L;

        Account account = Account.builder()
              .id(accountId)
              .name("accountName")
              .balance(100.0)
              .address("accountAddress")
              .build();

        TransactionDto transactionDto = TransactionDto.builder()
              .description("transactionDescription")
              .reference(0)
              .amount(-100.0)
              .transactionDate(LocalDate.now())
              .build();

        double newBalance = account.getBalance() + transactionDto.getAmount();

        Transaction transaction = mapToEntity(transactionDto);
        transaction.setId(transactionId);
        transaction.setAccount(account);
        transaction.setBalance(newBalance);

        when(accountRepositoryMock.findById(anyLong())).thenReturn(Optional.of(account));
        when(transactionRepositoryMock.save(any(Transaction.class))).thenReturn(transaction);

        // WHEN
        TransactionDto result = sut.createTransaction(accountId, transactionDto);

        // THEN
        verify(accountRepositoryMock, times(1)).save(any(Account.class));
        verify(transactionRepositoryMock, times(1)).save(any(Transaction.class));
        assertEquals(0.0, account.getBalance());
        assertEquals(0.0, result.getBalance());
        assertEquals(0.0, result.getAccount().getBalance());
    }

    @Test
    void getAllTransactions() {
    }

    @Test
    void getAllTransactionsBetween() {
    }

    @Test
    void getTransactionById() {
    }

    @Test
    void deleteTransaction() {
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