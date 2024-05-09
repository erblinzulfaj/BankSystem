package com.banksystem.mappers;

import com.banksystem.dtos.TransactionDto;
import com.banksystem.models.Account;
import com.banksystem.models.Transaction;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public Transaction toEntity(TransactionDto dto){
        Transaction transaction = new Transaction();
        transaction.setId(dto.getId());
        transaction.setAmount(dto.getAmount());
        transaction.setTransactionReason(dto.getTransactionReason());
        transaction.setOriginatingAccount(dto.getOriginatingAccount());
        transaction.setResultingAccount(dto.getResultingAccount());
        return transaction;
    }
    public TransactionDto toDto(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setTransactionReason(transaction.getTransactionReason());
        transactionDto.setOriginatingAccount(transaction.getOriginatingAccount());
        transactionDto.setResultingAccount(transaction.getResultingAccount());
        return transactionDto;
    }
}
