package com.banksystem.models.helpers;


import com.banksystem.models.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionalHelper {
    private Long id;
    private double amount;
    private String transactionReason;

    private long originatingAccountId;

    private long resultingAccountId;

}
