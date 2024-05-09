package com.banksystem.services;

import com.banksystem.dtos.BankDto;

import java.util.List;

public interface BankService {
 void createBank(BankDto bankDto);

 List<BankDto> getAllBankAccounts();

  double getTotalTransactionFeeAmount(Long bankId);

  double getTotalTransferAmount(Long bankId);
}
