package com.chuwa.bank.controller;

import com.chuwa.bank.dto.AccountDto;
import com.chuwa.bank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/accounts/")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return null;
    }

    @GetMapping("")
    public List<AccountDto> getAllAccounts() {
        return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable(name = "id") long id) {
        return null;
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable(name = "id") long id,
                                                    @RequestBody AccountDto accountDto) {
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable(name = "id") long id) {
        return null;
    }
}
