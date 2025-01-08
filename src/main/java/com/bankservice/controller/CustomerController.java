package com.bankservice.controller;

import com.bankservice.ModelAssembler.CustomerModelAssembler;
import com.bankservice.model.Customer;
import com.bankservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerModelAssembler customerModelAssembler;

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
        Customer newCustomer = customerService.addNewCustomer(customer);
        EntityModel<Customer> entityModel = customerModelAssembler.toModel(customer);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping
    public CollectionModel<EntityModel<Customer>> fetchAllCustomers(){
        List<EntityModel<Customer>> customers = customerService.allCustomers().stream()
                .map(customerModelAssembler::toModel).toList();
        return CollectionModel.of(customers,
                linkTo(methodOn(CustomerController.class).fetchAllCustomers()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Customer> fetchCustomerById(@PathVariable Long id){
        Customer customer = customerService.findCustomerById(id);
        return customerModelAssembler.toModel(customer);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> replaceCustomer(@RequestBody Customer customer, @PathVariable Long id){
        Customer updatedCustomer = customerService.updateCustomerById(customer, id);
        EntityModel<Customer> entityModel = customerModelAssembler.toModel(updatedCustomer);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }


}
