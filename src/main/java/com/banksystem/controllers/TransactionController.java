package com.banksystem.controllers;

import com.banksystem.dtos.BankDto;
import com.banksystem.dtos.TransactionDto;
import com.banksystem.models.Transaction;
import com.banksystem.models.helpers.Helper;
import com.banksystem.models.helpers.TransactionalHelper;
import com.banksystem.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin("*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/perform-action")
    public void performTransaction(@RequestBody TransactionalHelper transactionDto){
        System.out.println(transactionDto);
        transactionService.performTransaction(transactionDto);
    }



    @PutMapping("/withdraw/{id}")
    public void withdrawMoney(@PathVariable long id, @RequestBody Helper withdraw){
        double amount = withdraw.getAmount();
        transactionService.withdrawMoney(id, amount);
    }
    @PutMapping("/deposit/{id}")
    public void depositMoney(@PathVariable long id, @RequestBody Helper deposit){
        double amount = deposit.getAmount();
        transactionService.depositMoney(id, amount);
    }
    @GetMapping
    public List<TransactionDto> getAllTransactions(){
            return transactionService.getAllTransactions();

    }

}
