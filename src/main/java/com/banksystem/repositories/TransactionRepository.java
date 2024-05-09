package com.banksystem.repositories;

import com.banksystem.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t INNER JOIN t.originatingAccount a WHERE a.id = :accountId")
    List<Transaction> findByOriginatingAccountId(@Param("accountId") Long accountId);

    @Query("SELECT t FROM Transaction t INNER JOIN t.resultingAccount a WHERE a.id = :accountId")
    List<Transaction> findByResultingAccountId(@Param("accountId") Long accountId);

}
