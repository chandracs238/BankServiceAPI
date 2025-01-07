package com.bankservice.controller;

import com.bankservice.model.Branch;
import com.bankservice.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping
    public Branch newBranch(Branch branch){
        return branchService.addNewBranch(branch);
    }

    @GetMapping
    public List<Branch> fetchAllBranches(){
        return branchService.allBranchs();
    }

    @GetMapping("/{id}")
    public Branch fetchBranchById(@PathVariable Long id){
        return branchService.findBranchById(id);
    }

    @PutMapping("/{id}")
    public Branch updateBranchById(@RequestBody Branch branch, @PathVariable Long id){
        return branchService.updateBranchById(branch, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBranchById(@PathVariable Long id){
        branchService.deleteBranchById(id);
    }

}
