package com.shosha.ecommerce.service;


import com.shosha.ecommerce.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer save(Customer customer);
    Customer update(Customer customer);
    List<Customer> findAll();
    Optional<Customer> findOne(Long id);
    void delete(Long id);
}
