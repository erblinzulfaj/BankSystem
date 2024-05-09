package com.banksystem.controllers;

import com.banksystem.dtos.AccountDto;
import com.banksystem.dtos.BankDto;
import com.banksystem.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
@CrossOrigin("*")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping
    public void createBank(@RequestBody BankDto bankDto){
        bankService.createBank(bankDto);
    }

    @GetMapping
    public List<BankDto> getAllBankAccounts(){
        return bankService.getAllBankAccounts();
    }

    @GetMapping("/total-transaction-fee/{id}")
    public double getTotalTransactionFeeAmount(@PathVariable long id){
        return bankService.getTotalTransactionFeeAmount(id);
    }

    @GetMapping("/total-transfer/{id}")
    public double getTotalTransferAmount(@PathVariable long id){
        return bankService.getTotalTransferAmount(id);
    }
}