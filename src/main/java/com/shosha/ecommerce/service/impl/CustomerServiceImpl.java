package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dao.CustomerRepository;
import com.shosha.ecommerce.entity.Customer;
import com.shosha.ecommerce.service.CustomerService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findOne(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
