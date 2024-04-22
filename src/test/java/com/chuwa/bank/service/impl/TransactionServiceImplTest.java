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
import java.util.List;
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
        // GIVEN
        long accountId = 9999;

        Account account = Account.builder()
              .id(accountId)
              .name("accountName")
              .balance(100.0)
              .address("accountAddress")
              .build();

        Transaction transaction1 = Transaction.builder()
              .id(1L)
              .account(account)
              .amount(100.0)
              .reference(1)
              .description("description")
              .transactionDate(LocalDate.now())
              .balance(100.0)
              .build();

        Transaction transaction2 = Transaction.builder()
              .id(2L)
              .account(account)
              .amount(100.0)
              .reference(2)
              .description("description")
              .transactionDate(LocalDate.now())
              .balance(100.0)
              .build();


        when(accountRepositoryMock.findById(anyLong())).thenReturn(Optional.of(account));
        when(transactionRepositoryMock.findAll()).thenReturn(List.of(transaction1, transaction2));

        // WHEN
        List<TransactionDto> result = sut.getAllTransactions(accountId);

        // THEN
        assertEquals(2, result.size());
    }

    @Test
    void getAllTransactionsBetween() {
//        // GIVEN
//        long accountId = 9999;
//
//        Account account = Account.builder()
//              .id(accountId)
//              .name("accountName")
//              .balance(100.0)
//              .address("accountAddress")
//              .build();
//
//        LocalDate startDate = LocalDate.of(2024, 4, 1);
//        LocalDate endDate = LocalDate.of(2024, 4, 30);
//
//
//        Transaction transaction1 = Transaction.builder()
//              .id(1L)
//              .account(account)
//              .amount(100.0)
//              .reference(1)
//              .description("description")
//              .transactionDate(LocalDate.of(2024, 4, 21))
//              .balance(100.0)
//              .build();
//
//        Transaction transaction2 = Transaction.builder()
//              .id(2L)
//              .account(account)
//              .amount(100.0)
//              .reference(2)
//              .description("description")
//              .transactionDate(LocalDate.of(2024, 3, 21))
//              .balance(100.0)
//              .build();
//
//
//        when(accountRepositoryMock.findById(anyLong())).thenReturn(Optional.of(account));
//        when(transactionRepositoryMock.findTransactionByTransactionDateBetween(startDate, endDate)).thenReturn(List.of(transaction1, transaction2));
//
//        // WHEN
//        List<TransactionDto> result = sut.getAllTransactionsBetween(accountId, startDate, endDate);
//
//        // THEN
//        assertEquals(1, result.size());
//        result.forEach(t -> assertTrue(t.getTransactionDate().isAfter(startDate) &&
//              t.getTransactionDate().isBefore(endDate)));
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