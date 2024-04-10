package com.chuwa.bank.controller;

import com.chuwa.bank.dto.StatementDto;
import com.chuwa.bank.service.StatementService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/statements/")
public class StatementController {

    private final StatementService statementService;

    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    @GetMapping("{accountId}/between")
    public ResponseEntity<StatementDto> getStatement(@PathVariable(name = "accountId") long accountId,
                                                     @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                     @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return new ResponseEntity<>(statementService.getStatement(accountId, startDate, endDate), HttpStatus.OK);
    }



}
