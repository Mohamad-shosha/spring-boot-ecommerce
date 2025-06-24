package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dao.AddressRepository;
import com.shosha.ecommerce.entity.Address;
import com.shosha.ecommerce.service.AddressService;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Address address) {
        return addressRepository.save(address);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Address> findOne(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}

