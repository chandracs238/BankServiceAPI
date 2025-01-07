package com.bankservice.controller;

import com.bankservice.model.Account;
import com.bankservice.model.Transaction;
import com.bankservice.service.AccountService;
import com.bankservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;

    @PostMapping("/transactions")
    public Transaction newTransaction(@RequestBody Transaction transaction){
        return transactionService.addNewTransaction(transaction);
    }

    @GetMapping("/transactions")
    public List<Transaction> allTransactions(){
        return transactionService.allTransactions();
    }

    @GetMapping("/transactions/{id}")
    public Transaction findTransactionById(@PathVariable Long id){
        return transactionService.findTransactionById(id);
    }

    @GetMapping("/accounts/{id}/transactions")
    public List<Transaction> allTransactionsByCustomer(@PathVariable Long id){
        Account account = accountService.findAccountById(id);
        return transactionService.allTransactionsByAccount(account);
    }

//    @PutMapping("/transactions/{id}/revert")
//    public Transaction
}
