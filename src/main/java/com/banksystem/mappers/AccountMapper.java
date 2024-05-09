package com.banksystem.mappers;

import com.banksystem.dtos.AccountDto;
import com.banksystem.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toEntity(AccountDto dto){
        Account account = new Account();
        account.setId(dto.getId());
        account.setUserName(dto.getUserName());
        account.setBalance(dto.getBalance());
        account.setBank(dto.getBank());
        account.setOutgoingTransactions(dto.getOutgoingTransactions());
        account.setIncomingTransactions(dto.getIncomingTransactions());
        return account;
    }
    public AccountDto toDto(Account account){
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setUserName(account.getUserName());
        accountDto.setBalance(account.getBalance());
        accountDto.setBank(account.getBank());
        accountDto.setOutgoingTransactions(account.getOutgoingTransactions());
        accountDto.setIncomingTransactions(account.getIncomingTransactions());
        return accountDto;
    }

}
