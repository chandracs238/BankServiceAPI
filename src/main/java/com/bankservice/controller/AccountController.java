package com.bankservice.controller;

import com.bankservice.model.Account;
import com.bankservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account createNewAccount(@RequestBody Account account){
        return accountService.addNewAccount(account);
    }

    @GetMapping
    public List<Account> fetchAllAccount(){
        return accountService.allAccounts();
    }

    @GetMapping("/{id}")
    public Account fetchAccountById(@PathVariable Long id){
        return accountService.findAccountById(id);
    }

    @PutMapping("/{id}")
    public Account updateAccountById(@RequestBody Account account, @PathVariable Long id){
        return accountService.updateAccountById(account, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAccountById(@PathVariable Long id){
        accountService.deleteAccount(id);
    }

}
