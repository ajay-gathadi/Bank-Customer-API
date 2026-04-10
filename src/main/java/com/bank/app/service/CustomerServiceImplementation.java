package com.bank.app.service;

import com.bank.app.model.Customer;
import com.bank.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService{

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImplementation(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


}
