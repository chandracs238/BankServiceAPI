package com.bankservice.service;

import com.bankservice.exception.AccountNotFoundException;
import com.bankservice.model.Account;
import com.bankservice.model.Branch;
import com.bankservice.model.Customer;
import com.bankservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> allAccounts(){
        return accountRepository.findAll();
    }

    public List<Account> accountsByCustomer(Customer customer){
        return accountRepository.findAllByCustomer(customer);
    }

    public List<Account> accountsByBranch(Branch branch){
        return  accountRepository.findAllByBranch(branch);
    }

    public Account findAccountById(Long id){
        return  accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    public Account addNewAccount(Account account){
        return accountRepository.save(account);
    }

    public Account updateAccountById(Account account, Long id){
        accountRepository.deleteById(id);
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }
}
