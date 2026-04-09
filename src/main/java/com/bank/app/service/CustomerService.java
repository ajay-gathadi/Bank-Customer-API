package com.bank.app.service;

import com.bank.app.model.Customer;
import com.bank.app.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    void createCustomer(Customer customer);
    public List<CustomerRepository> getAllCustomers();
    public Long getCustomerById();

}
