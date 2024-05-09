package com.banksystem.services;

import com.banksystem.dtos.BankDto;
import com.banksystem.mappers.BankMapper;
import com.banksystem.models.Bank;
import com.banksystem.repositories.BankRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BankServiceTest {

    private final BankMapper bankMapper;
    private final BankService bankService;
    private final BankRepository bankRepository;

    BankServiceTest(BankMapper bankMapper, BankService bankService, BankRepository bankRepository) {
        this.bankMapper = bankMapper;
        this.bankService = bankService;
        this.bankRepository = bankRepository;
    }

    @Test
    void createBank() {
        BankDto bankDto = new BankDto();
        bankDto.setName("Test Bank");
        bankDto.setTotalTransactionFeeAmount(50.0);
        bankDto.setTotalTransferAmount(1000.0);
        bankDto.setTransactionFlatFeeAmount(10.0);
        bankDto.setTransactionPercentFeeValue(0.05);

        Bank bank = new Bank();
        bank.setId(1L);
        bank.setName("Teb");
        bank.setTotalTransactionFeeAmount(0);
        bank.setTotalTransferAmount(0);
        bank.setTransactionFlatFeeAmount(10.0);
        bank.setTransactionPercentFeeValue(0.05);

        when(bankMapper.toEntity(bankDto)).thenReturn(bank);

        bankService.createBank(bankDto);
        verify(bankMapper).toEntity(bankDto);
        verify(bankRepository).save(bank);
    }
    }
