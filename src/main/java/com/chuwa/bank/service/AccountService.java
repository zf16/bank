package com.chuwa.bank.service;

import com.chuwa.bank.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    List<AccountDto> getAllAccounts();

    AccountDto getAccountById(long accountId);

    AccountDto updateAccount(long accountId, AccountDto accountDto);

    void deleteAccount(long accountId);

}
