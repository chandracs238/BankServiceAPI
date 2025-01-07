package com.bankservice.service;

import com.bankservice.exception.TransactionNotFoundException;
import com.bankservice.model.Account;
import com.bankservice.model.Transaction;
import com.bankservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> allTransactions(){
        return transactionRepository.findAll();
    }

    public List<Transaction> allTransactionsByAccount(Account account){
        return transactionRepository.findAllByAccount(account);
    }

    public Transaction findTransactionById(Long id){
        return transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
    }

    public Transaction addNewTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}
