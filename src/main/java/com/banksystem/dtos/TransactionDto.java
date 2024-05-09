package com.banksystem.dtos;


import com.banksystem.models.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Long id;
    private double amount;
    private String transactionReason;

    @ManyToOne
    @JoinColumn(name = "originating_account_id")
    @JsonIgnore
    private Account originatingAccount;

    @ManyToOne
    @JoinColumn(name = "resulting_account_id")
    @JsonIgnore
    private Account resultingAccount;

}
