package com.banksystem.repositories;

import com.banksystem.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    @Query("SELECT DISTINCT b FROM Bank b INNER JOIN FETCH b.accounts")
    List<Bank> findAllBanksWithAccounts();

    @Query("SELECT b.totalTransactionFeeAmount FROM Bank b WHERE b.id = ?1")
    double findTotalTransactionFeeAmountById(Long bankId);

    @Query("SELECT b.totalTransferAmount FROM Bank b WHERE b.id = ?1")
    double findTotalTransferAmountById(Long bankId);
}
