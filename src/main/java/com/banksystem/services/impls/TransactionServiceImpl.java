package com.banksystem.services.impls;

import com.banksystem.dtos.TransactionDto;
import com.banksystem.exceptions.AccountNotFound;
import com.banksystem.exceptions.NotEnoughFunds;
import com.banksystem.mappers.TransactionMapper;
import com.banksystem.models.Account;
import com.banksystem.models.Transaction;
import com.banksystem.models.helpers.TransactionalHelper;
import com.banksystem.repositories.AccountRepository;
import com.banksystem.repositories.BankRepository;
import com.banksystem.repositories.TransactionRepository;
import com.banksystem.services.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private TransactionMapper transactionMapper;


    //    @Transactional(rollbackOn = Exception.class)
//    @Override                          //ktu duhet me perdor dton
//    public void performTransaction(Long originatingAccountId, Long resultingAccountId, double amount, String transactionReason) {
//        var optionalOriginatingAccount = accountRepository.findById(originatingAccountId);
//        if (optionalOriginatingAccount.isEmpty()) {
//            throw new AccountNotFound("Account not found with id: " + originatingAccountId);
//        }
//
//        var optionalResultingAccount = accountRepository.findById(resultingAccountId);
//        if (optionalResultingAccount.isEmpty()) {
//            throw new AccountNotFound("Account not found with id: " + resultingAccountId);
//        }
//
//        var originatingAccount = optionalOriginatingAccount.get();
//        var resultingAccount = optionalResultingAccount.get();
//
//        if (originatingAccount.getBalance() < amount) {
//            throw new NotEnoughFunds("Originating account not have enough funds");
//        }
//
//        //fee=5
//        //flatfee=10
//
//        if (Objects.equals(originatingAccount.getBank().getId(), resultingAccount.getBank().getId())) {
//            var bankId = originatingAccount.getBank().getId();
//            var bankById = bankRepository.findById(bankId);
//            var bank = bankById.get();
//
//            originatingAccount.setBalance(originatingAccount.getBalance() + bank.getTransactionFlatFeeAmount() - amount);
//            accountRepository.save(originatingAccount);
//
////            bank.setTotalTransactionFeeAmount(bank.getTotalTransactionFeeAmount() + bank.getTransactionFlatFeeAmount());
//
//            resultingAccount.setBalance(resultingAccount.getBalance() + amount);
//            accountRepository.save(resultingAccount);
//
//            bank.setTotalTransactionFeeAmount(bank.getTotalTransactionFeeAmount() + bank.getTransactionFlatFeeAmount());
//            bank.setTotalTransferAmount(bank.getTotalTransferAmount() + amount);
//
//        } else {
//            var originatingBankId = originatingAccount.getBank().getId();
//            var originatingBankById = bankRepository.findById(originatingBankId);
//            var originatingBank = originatingBankById.get();
//
//            var resultingBankId = resultingAccount.getBank().getId();
//            var resultingBankById = bankRepository.findById(resultingBankId);
//            var resultingBank = resultingBankById.get();
//
//            var value = amount * (originatingBank.getTransactionPercentFeeValue() / 100);
//
//            var resultTotal = resultingBank.getTotalTransferAmount();
//            resultTotal += amount;
//            resultingBank.setTotalTransferAmount(resultTotal);
//
//
//            originatingAccount.setBalance(originatingAccount.getBalance() - amount + value);
//            resultingAccount.setBalance(resultingAccount.getBalance() + amount);
//            accountRepository.save(originatingAccount);
//            accountRepository.save(resultingAccount);
//
//            originatingBank.setTotalTransactionFeeAmount(originatingBank.getTotalTransactionFeeAmount() + value);
//            originatingBank.setTotalTransferAmount(originatingBank.getTotalTransferAmount() + amount);
//        }
//
//        TransactionDto transaction = new TransactionDto();
//        transaction.setAmount(amount);
//        transaction.setOriginatingAccount(originatingAccount);
//        transaction.setResultingAccount(resultingAccount);
//        transaction.setTransactionReason(transactionReason);
//        transactionRepository.save(transactionMapper.toEntity(transaction));
//    }
    @Override
    public void performTransaction(TransactionalHelper transactionDto) {
        System.out.println(transactionDto);
        long id = transactionDto.getOriginatingAccountId();
        var optionalOriginatingAccount = accountRepository.findById(id);
        if (optionalOriginatingAccount.isEmpty()) {
            throw new AccountNotFound("Account not found with id: " + id);
        }

        var rId = transactionDto.getResultingAccountId();
        var optionalResultingAccount = accountRepository.findById(rId);
        if (optionalResultingAccount.isEmpty()) {
            throw new AccountNotFound("Account not found with id: " + rId);
        }

        var originatingAccount = optionalOriginatingAccount.get();
        var resultingAccount = optionalResultingAccount.get();

        if (originatingAccount.getBalance() < transactionDto.getAmount()) {
            throw new NotEnoughFunds("Originating account not have enough funds");
        }

        if (Objects.equals(originatingAccount.getBank().getId(), resultingAccount.getBank().getId())) {
            var bankId = originatingAccount.getBank().getId();
            var bankById = bankRepository.findById(bankId);
            var bank = bankById.get();

            double sum = bank.getTransactionFlatFeeAmount() + transactionDto.getAmount();
            originatingAccount.setBalance(originatingAccount.getBalance() - sum);
            accountRepository.save(originatingAccount);

//            bank.setTotalTransactionFeeAmount(bank.getTotalTransactionFeeAmount() + bank.getTransactionFlatFeeAmount());

            resultingAccount.setBalance(resultingAccount.getBalance() + transactionDto.getAmount());
            accountRepository.save(resultingAccount);

            bank.setTotalTransactionFeeAmount(bank.getTotalTransactionFeeAmount() + bank.getTransactionFlatFeeAmount());
            bank.setTotalTransferAmount(bank.getTotalTransferAmount() + transactionDto.getAmount());
        } else {
            var originatingBankId = originatingAccount.getBank().getId();
            var originatingBankById = bankRepository.findById(originatingBankId);
            var originatingBank = originatingBankById.get();

            var resultingBankId = resultingAccount.getBank().getId();
            var resultingBankById = bankRepository.findById(resultingBankId);
            var resultingBank = resultingBankById.get();

            double value = originatingBank.getTransactionPercentFeeValue() * (transactionDto.getAmount() / 100);

            var resultTotal = resultingBank.getTotalTransferAmount();
            resultTotal += transactionDto.getAmount();
            resultingBank.setTotalTransferAmount(resultTotal);


            originatingAccount.setBalance(originatingAccount.getBalance() - transactionDto.getAmount() + value);
            resultingAccount.setBalance(resultingAccount.getBalance() + transactionDto.getAmount());
            accountRepository.save(originatingAccount);
            accountRepository.save(resultingAccount);

            originatingBank.setTotalTransactionFeeAmount(originatingBank.getTotalTransactionFeeAmount() + value);
            originatingBank.setTotalTransferAmount(originatingBank.getTotalTransferAmount() + transactionDto.getAmount());
        }
        TransactionDto transaction = new TransactionDto();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setOriginatingAccount(originatingAccount);
        transaction.setResultingAccount(resultingAccount);
        transaction.setTransactionReason(transactionDto.getTransactionReason());
        transactionRepository.save(transactionMapper.toEntity(transaction));
    }

    public void recordTransaction(Account originatingAccount, Account resultingAccount, double amount, String transactionReason) {
        TransactionDto transaction = new TransactionDto();
        transaction.setAmount(amount);
        transaction.setOriginatingAccount(originatingAccount);
        transaction.setResultingAccount(resultingAccount);
        transaction.setTransactionReason(transactionReason);
        transactionRepository.save(transactionMapper.toEntity(transaction));
    }

    @Override
    public void withdrawMoney(long id, double amount) {
        var optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            throw new AccountNotFound("Account not found with id: " + id);
        }
        var account = optionalAccount.get();
        if (account.getBalance() < amount) {
            throw new NotEnoughFunds("You don't have enough funds to withdraw " + amount + "$");
        }

        double balance = account.getBalance();
        double finalBalance = balance - amount;

        account.setBalance(finalBalance);
        accountRepository.save(account);

        recordTransaction(account, null, amount, "Withdraw");
    }

    @Override
    public void depositMoney(long accountId, double amount) {
        var optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()) {
            throw new AccountNotFound("Account not found with id: " + accountId);
        }
        var account = optionalAccount.get();

        double balance = account.getBalance();
        double finalBalance = balance + amount;

        account.setBalance(finalBalance);
        accountRepository.save(account);

        recordTransaction(null, account, amount, "Deposit");

    }


    @Override
    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream().map(transactionMapper::toDto).toList();
    }
}