package com.bankservice.controller;

import com.bankservice.ModelAssembler.TransactionModelAssembler;
import com.bankservice.model.Account;
import com.bankservice.model.Transaction;
import com.bankservice.service.AccountService;
import com.bankservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionModelAssembler transactionModelAssembler;

    @PostMapping("/transactions")
    public ResponseEntity<?> newTransaction(@RequestBody Transaction transaction){
        Transaction newTransaction = transactionService.addNewTransaction(transaction);
        EntityModel<Transaction> entityModel = transactionModelAssembler.toModel(newTransaction);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/transactions")
    public CollectionModel<EntityModel<Transaction>> allTransactions(){
        List<EntityModel<Transaction>> transactions = transactionService.allTransactions().stream()
                .map(transactionModelAssembler::toModel).toList();
        return CollectionModel.of(transactions,
                linkTo(methodOn(TransactionController.class).allTransactions()).withSelfRel());
    }

    @GetMapping("/transactions/{id}")
    public EntityModel<Transaction> findTransactionById(@PathVariable Long id){
        Transaction transaction = transactionService.findTransactionById(id);
        return transactionModelAssembler.toModel(transaction);
    }

    @GetMapping("/accounts/{id}/transactions")
    public CollectionModel<EntityModel<Transaction>> allTransactionsByAccount(@PathVariable Long id){
        Account account = accountService.findAccountById(id);
        List<EntityModel<Transaction>> transactions = transactionService.allTransactionsByAccount(account)
                .stream().map(transactionModelAssembler::toModel).toList();
        return CollectionModel.of(transactions,
                linkTo(methodOn(TransactionController.class)).withSelfRel());
    }
}
