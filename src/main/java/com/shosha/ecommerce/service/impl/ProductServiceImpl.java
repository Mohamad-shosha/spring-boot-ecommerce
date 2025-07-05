package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dao.ProductRepository;
import com.shosha.ecommerce.dto.ProductDTO;
import com.shosha.ecommerce.entity.Product;
import com.shosha.ecommerce.service.ProductService;
import com.shosha.ecommerce.service.mapper.ProductMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.debug("Request to save Product : {}", productDTO);
        var product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public Optional<ProductDTO> update(ProductDTO productDTO) {
        log.debug("Request to update Product : {}", productDTO);
        Long id = productDTO.getId();
        if (id == null || !productRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot update; product not found (id=" + id + ")");
        }
        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return Optional.of(productMapper.toDto(product));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        return productRepository.findAll(pageable).map(productMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDTO> findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        return productRepository.findById(id).map(productMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete; product not found (id=" + id + ")");
        }
        productRepository.deleteById(id);
    }

    @Override
    public Optional<ProductDTO> partialUpdate(ProductDTO dto) {
        log.debug("Request to partially update Product : {}", dto);
        return productRepository
                .findById(dto.getId())
                .map(existing -> {
                    productMapper.partialUpdate(existing, dto);
                    return existing;
                })
                .map(productMapper::toDto);
    }
}
