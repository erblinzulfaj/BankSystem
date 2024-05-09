package com.banksystem.repositories;

import com.banksystem.dtos.AccountDto;
import com.banksystem.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
//    @Query("SELECT a.id, a.userName , a.balance  FROM Account a WHERE a.id = ?1")
//    AccountDto findBalanceByAccountId(Long accountId);
}
