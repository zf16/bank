package com.chuwa.bank.service;

import com.chuwa.bank.dto.StatementDto;

import java.time.LocalDate;

public interface StatementService {
    StatementDto getStatement(long accountId, LocalDate startDate, LocalDate endDate);
}
