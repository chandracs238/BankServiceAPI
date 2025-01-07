package com.bankservice.controller;

import com.bankservice.model.Customer;
import com.bankservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.addNewCustomer(customer);
    }

    @GetMapping
    public List<Customer> fetchAllCustomers(){
        return customerService.allCustomers();
    }

    @GetMapping("/{id}")
    public Customer fetchCustomerById(@PathVariable Long id){
        return  customerService.findCustomerById(id);
    }

    @PutMapping("/{id}")
    public  Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long id){
        return  customerService.updateCustomerById(customer, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }


}
