package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dao.CustomerRepository;
import com.shosha.ecommerce.entity.Customer;
import com.shosha.ecommerce.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer save(final Customer customer) {
        log.info("Saving new customer: {}", customer);
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Customer update(final Customer customer) {
        log.info("Updating customer with ID: {}", customer.getId());
        customerRepository.findById(customer.getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + customer.getId()));
        return customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        log.info("Fetching all customers");
        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findOne(final Long id) {
        log.info("Fetching customer with ID: {}", id);
        return customerRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        log.info("Deleting customer with ID: {}", id);
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete — customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
}
