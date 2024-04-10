package com.chuwa.bank.service.impl;

import com.chuwa.bank.dao.AccountRepository;
import com.chuwa.bank.dto.AccountDto;
import com.chuwa.bank.entity.Account;
import com.chuwa.bank.exception.ResourceNotFoundException;
import com.chuwa.bank.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = mapToEntity(accountDto);
        Account savedAccount = accountRepository.save(account);
        return mapToDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream()
              .map(this::mapToDto)
              .collect(Collectors.toList());
    }

    @Override
    public AccountDto getAccountById(long accountId) {
        Account account =  accountRepository.findById(accountId)
              .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));

        return mapToDto(account);
    }

    @Override
    public AccountDto updateAccount(long accountId, AccountDto accountDto) {
        Account account =  accountRepository.findById(accountId)
              .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));

        account.setName(accountDto.getName());
        account.setAddress(accountDto.getAddress());
        account.setBalance(accountDto.getBalance());

        Account updatedAccount = accountRepository.save(account);
        return mapToDto(updatedAccount);
    }

    @Override
    public void deleteAccount(long accountId) {
        Account account =  accountRepository.findById(accountId)
              .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));

        accountRepository.delete(account);
    }

    private Account mapToEntity(AccountDto accountDto) {

        Account account = new Account();
        account.setName(accountDto.getName());
        account.setAddress(accountDto.getAddress());

        return account;
    }

    private AccountDto mapToDto(Account account) {

        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setName(account.getName());
        accountDto.setAddress(account.getAddress());
        accountDto.setBalance(account.getBalance());

        return accountDto;
    }
}
