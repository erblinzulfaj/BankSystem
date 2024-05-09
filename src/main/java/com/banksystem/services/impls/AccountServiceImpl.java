package com.banksystem.services.impls;

import com.banksystem.dtos.AccountDto;
import com.banksystem.exceptions.AccountNotFound;
import com.banksystem.exceptions.NotEnoughFunds;
import com.banksystem.mappers.AccountMapper;
import com.banksystem.models.Account;
import com.banksystem.models.Transaction;
import com.banksystem.repositories.AccountRepository;
import com.banksystem.repositories.TransactionRepository;
import com.banksystem.services.AccountService;
import com.banksystem.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;


    @Override
    public void addAccount(AccountDto accountDto) {
        double balance = accountDto.getBalance();
        if (balance < 0) {
            throw new RuntimeException("You cannot add an account with balance less than zero");
        } else {
            var account = accountMapper.toEntity(accountDto);
            accountRepository.save(account);
        }
    }



@Override
public AccountDto getAccountBalanceById(Long id) {
    var optionalAccount = accountRepository.findById(id);
    if (optionalAccount.isEmpty()){
        throw new AccountNotFound("Account not found!");
    }
    var account = optionalAccount.get();
    var accountDto = new AccountDto();
    accountDto.setId(account.getId());
    accountDto.setUserName(account.getUserName());
    accountDto.setBalance(account.getBalance());
    return accountDto;

}

}
