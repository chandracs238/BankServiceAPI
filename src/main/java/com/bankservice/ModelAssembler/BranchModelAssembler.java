package com.bankservice.ModelAssembler;

import com.bankservice.controller.BranchController;
import com.bankservice.model.Branch;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BranchModelAssembler implements RepresentationModelAssembler<Branch, EntityModel<Branch>> {
    @Override
    public EntityModel<Branch> toModel(Branch branch) {
        return EntityModel.of(branch,
                linkTo(methodOn(BranchController.class).fetchBranchById(branch.getBranchId())).withSelfRel(),
                linkTo(methodOn(BranchController.class).fetchAllBranches()).withRel("branches"));
    }
}
