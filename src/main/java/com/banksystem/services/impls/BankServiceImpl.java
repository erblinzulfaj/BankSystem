package com.banksystem.services.impls;

import com.banksystem.dtos.BankDto;
import com.banksystem.mappers.BankMapper;
import com.banksystem.repositories.BankRepository;
import com.banksystem.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private BankMapper bankMapper;

    @Override
    public void createBank(BankDto bankDto) {
        var bank = bankMapper.toEntity(bankDto);
        bankRepository.save(bank);
    }

    @Override
    public List<BankDto> getAllBankAccounts() {
        return bankRepository.findAllBanksWithAccounts().stream().map(bankMapper::toDto).toList();
    }

    @Override
    public double getTotalTransactionFeeAmount(Long bankId) {
        return bankRepository.findTotalTransactionFeeAmountById(bankId);
    }

    @Override
    public double getTotalTransferAmount(Long bankId) {
        return bankRepository.findTotalTransferAmountById(bankId);
    }

}
