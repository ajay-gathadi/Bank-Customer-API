package com.bank.app.controller;

import com.bank.app.model.Customer;
import com.bank.app.service.CustomerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService  customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/api/customers")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer){
        String createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>("Customer created successfully.", HttpStatus.CREATED);
    }

    @GetMapping("/api/customers/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") long customerId){
        Customer customerwithID = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerwithID, HttpStatus.OK);
    }

    @PutMapping("/api/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") long customerId,
                                                   @RequestBody Customer customer){
        Customer customerwithID = customerService.updateCustomer(customerId, customer);
        return new ResponseEntity<>(customerwithID, HttpStatus.OK);
    }

    @GetMapping("/api/customers")
    public ResponseEntity<?> getAllCustomers(){
        List<Customer> customerList = customerService.getAllCustomers();

        if(customerList.isEmpty()){
            return new ResponseEntity<>("There is no data yet", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

}
