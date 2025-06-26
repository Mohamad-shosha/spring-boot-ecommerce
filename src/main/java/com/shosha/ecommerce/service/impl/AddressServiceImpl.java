package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dao.AddressRepository;
import com.shosha.ecommerce.entity.Address;
import com.shosha.ecommerce.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public Address save(final Address address) {
        log.info("Saving new address: {}", address);
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address update(final Address address) {
        log.info("Updating address with ID: {}", address.getId());
        addressRepository.findById(address.getId())
                .orElseThrow(() -> new IllegalArgumentException("Address not found with id: " + address.getId()));
        return addressRepository.save(address);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findAll() {
        log.info("Fetching all addresses");
        return addressRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Address> findOne(final Long id) {
        log.info("Fetching address with ID: {}", id);
        return addressRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        log.info("Deleting address with ID: {}", id);
        if (!addressRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete — address not found with id: " + id);
        }
        addressRepository.deleteById(id);
    }
}
