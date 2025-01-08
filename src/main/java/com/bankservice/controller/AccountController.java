package com.bankservice.controller;

import com.bankservice.ModelAssembler.AccountModelAssembler;
import com.bankservice.model.Account;
import com.bankservice.service.AccountService;
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
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountModelAssembler accountModelAssembler;

    @PostMapping
    public ResponseEntity<?> createNewAccount(@RequestBody Account account){
        Account newAccount = accountService.addNewAccount(account);
        EntityModel<Account> entityModel = accountModelAssembler.toModel(newAccount);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping
    public CollectionModel<EntityModel<Account>> fetchAllAccount(){
        List<EntityModel<Account>> accounts = accountService.allAccounts().stream()
                .map(accountModelAssembler::toModel).toList();
        return CollectionModel.of(accounts, linkTo(methodOn(AccountController.class).fetchAllAccount()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Account> fetchAccountById(@PathVariable Long id){
        Account account = accountService.findAccountById(id);
        return accountModelAssembler.toModel(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> ReplaceAccountById(@RequestBody Account account, @PathVariable Long id){
        Account updatedAccount = accountService.updateAccountById(account, id);
        EntityModel<Account> entityModel = accountModelAssembler.toModel(updatedAccount);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccountById(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

}
