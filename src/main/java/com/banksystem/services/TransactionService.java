package com.banksystem.services;

import com.banksystem.dtos.TransactionDto;
import com.banksystem.models.Account;
import com.banksystem.models.helpers.TransactionalHelper;

import java.util.List;

public interface TransactionService {
//    void performTransaction(Long originatingAccountId, Long resultingAccountId, double amount, String transactionReason);
void performTransaction(TransactionalHelper transactionDto);

    List<TransactionDto> getAllTransactions();

    void withdrawMoney(long accountId, double amount);

    void depositMoney(long accountId, double amount);
}
