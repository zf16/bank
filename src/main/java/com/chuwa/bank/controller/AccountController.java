package com.chuwa.bank.controller;

import com.chuwa.bank.dto.AccountDto;
import com.chuwa.bank.service.AccountService;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable(name = "id") long id,
                                                    @RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.updateAccount(id, accountDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable(name = "id") long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
    }
}
