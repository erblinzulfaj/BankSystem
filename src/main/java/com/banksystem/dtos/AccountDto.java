package com.banksystem.dtos;

import com.banksystem.models.Bank;
import com.banksystem.models.Transaction;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String userName;
    private double balance;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToMany(mappedBy = "originatingAccount")
    private List<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "resultingAccount")
    private List<Transaction> incomingTransactions;
}
