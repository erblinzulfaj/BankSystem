package com.banksystem.controllers;


import com.banksystem.dtos.AccountDto;
import com.banksystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@CrossOrigin("*")
public class AccountController {


    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //    @PostMapping
//    public void addAccount(@RequestBody AccountDto accountDto){
//        accountService.addAccount(accountDto);
//    }
    @PostMapping("/addAccount")
    public ResponseEntity<Void> addAccount(@RequestBody AccountDto accountDto) {
        accountService.addAccount(accountDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public AccountDto getAccnoutBalanceById(@PathVariable long id) {
        return accountService.getAccountBalanceById(id);
    }
}
