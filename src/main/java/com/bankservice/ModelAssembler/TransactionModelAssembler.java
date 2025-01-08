package com.bankservice.ModelAssembler;

import com.bankservice.controller.TransactionController;
import com.bankservice.model.Transaction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TransactionModelAssembler implements RepresentationModelAssembler<Transaction, EntityModel<Transaction>> {

    @Override
    public EntityModel<Transaction> toModel(Transaction transaction) {
        return EntityModel.of(transaction,
                linkTo(methodOn(TransactionController.class).findTransactionById(transaction.getTransactionId())).withSelfRel(),
                linkTo(methodOn(TransactionController.class).allTransactions()).withRel("transactions"));
    }
}
