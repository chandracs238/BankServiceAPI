package com.bankservice.loadDatabase;

import com.bankservice.model.*;
import com.bankservice.repository.AccountRepository;
import com.bankservice.repository.BranchRepository;
import com.bankservice.repository.CustomerRepository;
import com.bankservice.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoadDatabase{

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);


    @Bean
    @Transactional
    CommandLineRunner initDatabase(AccountRepository accountRepository, BranchRepository branchRepository, CustomerRepository customerRepository, TransactionRepository transactionRepository){
        return args -> {
            try {
                Customer customer = customerRepository.save(new Customer("Dinesh", "dinesh@gmail.com"));
                log.info("Customer created: {}", customer);

                Branch branch = branchRepository.save(new Branch("Kakinada Branch", "Kakinada", 7013409936L));
                log.info("Branch created: {}", branch);

                Account account = accountRepository.save(new Account(AccountType.SAVINGS,
                        BigDecimal.valueOf(9000), LocalDate.now(), customer, branch));
                log.info("Account created: {}", account);

                Transaction transaction = transactionRepository.save(new Transaction(BigDecimal.valueOf(300),
                        TransactionType.DEPOSIT, account));
                log.info("Transaction created: {}", transaction);
            } catch (Exception e) {
                log.error("Error during database initialization", e);
            }
        };
    }

}
