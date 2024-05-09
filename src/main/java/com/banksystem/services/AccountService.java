package com.banksystem.services;

import com.banksystem.dtos.AccountDto;
import com.banksystem.models.Account;

import java.util.List;

public interface AccountService {
 void addAccount(AccountDto accountDto);
 AccountDto getAccountBalanceById(Long id);

}
