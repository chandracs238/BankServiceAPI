package com.bankservice.entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {

    @Test
    void testAddMoney(){
        // Arrange
        Customer customer = new Customer("Chandra", "chandra2@gmail.com");
        Branch branch = new Branch("Monikonda", "Monikonda", 70134099L);
        Account account = new Account(AccountType.SAVINGS, BigDecimal.valueOf(1000), LocalDate.now(), customer, branch);

        BigDecimal initialBalance = account.getBalance();
        BigDecimal addMoney = new BigDecimal("4000");

        // Act
        account.addMoney(addMoney);

        // Assert
        BigDecimal expectedBalance = initialBalance.add(addMoney);
        assertEquals(expectedBalance, account.getBalance());
    }
}
