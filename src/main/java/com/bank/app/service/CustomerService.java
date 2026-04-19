package com.bank.app.service;

import com.bank.app.model.Customer;

import java.util.List;

public interface CustomerService {
    String createCustomer(Customer customer);
    public List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);
    Customer updateCustomer(Long customerId, Customer customer);
    Customer deleteCustomer(Long customerId);

}
