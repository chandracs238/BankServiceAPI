package com.bankservice.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private BigDecimal amount;
    private TransactionType type;
    private LocalDate date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Transaction(){}

    public Transaction(BigDecimal amount, TransactionType type, LocalDate date, String description, Account account){
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.description = description;
        this.account = account;
    }

    public Transaction(BigDecimal amount, TransactionType type, LocalDate date, Account account){
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.account = account;
    }

    public Transaction(BigDecimal amount, TransactionType type, Account account){
        this.amount = amount;
        this.type = type;
        this.date = LocalDate.now();
        this.account = account;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", type=" + type +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", account=" + account +
                '}';
    }
}
