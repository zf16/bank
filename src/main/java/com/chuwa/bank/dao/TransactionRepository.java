package com.chuwa.bank.dao;

import com.chuwa.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionByTransactionDateBetween(LocalDate startDate, LocalDate endDate);

}
