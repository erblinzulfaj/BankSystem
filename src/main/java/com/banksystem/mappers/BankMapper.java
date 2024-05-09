package com.banksystem.mappers;
import com.banksystem.dtos.BankDto;
import com.banksystem.models.Account;
import com.banksystem.models.Bank;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankMapper {

     public Bank toEntity(BankDto dto){
         Bank bank = new Bank();
         bank.setId(dto.getId());
         bank.setName(dto.getName());
         bank.setTotalTransactionFeeAmount(dto.getTotalTransactionFeeAmount());
         bank.setTotalTransferAmount(dto.getTotalTransferAmount());
         bank.setTransactionFlatFeeAmount(dto.getTransactionFlatFeeAmount());
         bank.setTransactionPercentFeeValue(dto.getTransactionPercentFeeValue());
         return bank;
     }
     public BankDto toDto(Bank bank){
         BankDto dto = new BankDto();
         dto.setId(bank.getId());
         dto.setName(bank.getName());
         dto.setTotalTransactionFeeAmount(bank.getTotalTransactionFeeAmount());
         dto.setTotalTransferAmount(bank.getTotalTransferAmount());
         dto.setTransactionFlatFeeAmount(bank.getTransactionFlatFeeAmount());
         dto.setTransactionPercentFeeValue(bank.getTransactionPercentFeeValue());

         List<Long> accountIds = bank.getAccounts().stream()
                 .map(Account::getId)
                 .collect(Collectors.toList());
         dto.setAccounts(accountIds);
         return dto;
     }



}
