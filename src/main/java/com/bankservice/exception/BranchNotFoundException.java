package com.bankservice.exception;

import com.bankservice.model.Branch;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BranchNotFoundException extends RuntimeException {
    public BranchNotFoundException(Long id) {
        super("Branch not found of Id: " + id);
    }

    public BranchNotFoundException(String message){
        super("Branch not found");
    }
}
