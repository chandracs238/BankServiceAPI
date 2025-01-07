package com.bankservice.repository;

import com.bankservice.model.Account;
import com.bankservice.model.Branch;
import com.bankservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByCustomer(Customer customer);
    List<Account> findAllByBranch(Branch branch);
}
