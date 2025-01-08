package com.bankservice.ModelAssembler;

import com.bankservice.controller.AccountController;
import com.bankservice.model.Account;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AccountModelAssembler implements RepresentationModelAssembler<Account, EntityModel<Account>> {

    @Override
    public EntityModel<Account> toModel(Account account) {
        return EntityModel.of(account,
                linkTo(methodOn(AccountController.class).fetchAccountById(account.getAccountId())).withSelfRel(),
                linkTo(methodOn(AccountController.class).fetchAllAccount()).withRel("accounts"));
    }

}
