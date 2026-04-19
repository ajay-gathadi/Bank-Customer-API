package com.bank.app.service;

import com.bank.app.model.Customer;
import com.bank.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService{

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImplementation(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public String createCustomer(Customer customer) {
        Customer createdCustomer = customerRepository.save(customer);
        return createdCustomer.toString();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);

        if(customer.isPresent()){
            return customer.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found");
        }
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customer){
        Customer updateCustomer = getCustomerById(customerId);
        updateCustomer.setFirstName(customer.getFirstName());
        updateCustomer.setLastName(customer.getLastName());
        customerRepository.save(updateCustomer);
        return updateCustomer;
    }

    @Override
    public Customer deleteCustomer(Long customerId) {
        Customer deleteCustomer = getCustomerById(customerId);
        customerRepository.delete(deleteCustomer);
        return deleteCustomer;
    }


}
