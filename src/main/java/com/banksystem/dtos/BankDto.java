package com.banksystem.dtos;

import com.banksystem.models.Account;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDto {
    private Long id;
    private String name;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFeeAmount;
    private double transactionPercentFeeValue;

    @OneToMany(mappedBy = "bank")
    private List<Long> accounts;
}
