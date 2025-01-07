package com.bankservice.service;

import com.bankservice.exception.BranchNotFoundException;
import com.bankservice.model.Branch;
import com.bankservice.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> allBranchs(){
        return branchRepository.findAll();
    }

    public Branch findBranchById(Long id){
        return branchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException(id));
    }

    public Branch findBranchByLocation(String location){
        return branchRepository.findByLocation(location).orElseThrow(() -> new BranchNotFoundException(location));
    }

    public Branch findBranchByBranchName(String name){
        return branchRepository.findByBranchName(name).orElseThrow(() -> new BranchNotFoundException(name));
    }

    public Branch addNewBranch(Branch branch){
        return branchRepository.save(branch);
    }

    public void deleteBranchById(Long id){
        branchRepository.deleteById(id);
    }

    public Branch updateBranchById(Branch branch, Long id){
        branchRepository.deleteById(id);
        return branchRepository.save(branch);
    }

}
