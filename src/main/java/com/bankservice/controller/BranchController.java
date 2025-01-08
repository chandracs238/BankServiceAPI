package com.bankservice.controller;

import com.bankservice.ModelAssembler.BranchModelAssembler;
import com.bankservice.model.Branch;
import com.bankservice.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchModelAssembler branchModelAssembler;

    @PostMapping
    public ResponseEntity<?> createNewBranch(Branch branch){
        Branch newBranch = branchService.addNewBranch(branch);
        EntityModel<Branch> entityModel = branchModelAssembler.toModel(newBranch);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping
    public CollectionModel<EntityModel<Branch>> fetchAllBranches(){
        List<EntityModel<Branch>> branches = branchService.allBranchs().stream()
                .map(branchModelAssembler::toModel).toList();
        return CollectionModel.of(branches,
                linkTo(methodOn(BranchController.class).fetchAllBranches()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Branch> fetchBranchById(@PathVariable Long id){
        Branch branch = branchService.findBranchById(id);
        return branchModelAssembler.toModel(branch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceBranchById(@RequestBody Branch branch, @PathVariable Long id){
        Branch updatedBranch = branchService.updateBranchById(branch, id);
        EntityModel<Branch> entityModel = branchModelAssembler.toModel(updatedBranch);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBranchById(@PathVariable Long id){
        branchService.deleteBranchById(id);
        return ResponseEntity.noContent().build();
    }

}
