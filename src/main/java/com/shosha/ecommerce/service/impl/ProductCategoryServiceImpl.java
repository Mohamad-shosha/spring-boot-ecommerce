package com.shosha.ecommerce.service.impl;


import com.shosha.ecommerce.dao.ProductCategoryRepository;
import com.shosha.ecommerce.dto.ProductCategoryDTO;
import com.shosha.ecommerce.entity.Product;
import com.shosha.ecommerce.entity.ProductCategory;
import com.shosha.ecommerce.service.ProductCategoryService;
import com.shosha.ecommerce.service.mapper.ProductCategoryMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final Logger log = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);

    private final ProductCategoryRepository repository;
    private final ProductCategoryMapper mapper;

    public ProductCategoryServiceImpl(ProductCategoryRepository repository,
                                      ProductCategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProductCategoryDTO save(ProductCategoryDTO dto) {
        log.debug("Request to save ProductCategory : {}", dto);
        ProductCategory entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public Optional<ProductCategoryDTO> update(ProductCategoryDTO dto) {
        log.debug("Request to update Product : {}", dto);
        Long id = dto.getId();
        if (id == null || !repository.existsById(id)) {
            throw new IllegalArgumentException("Cannot update; category not found (id=" + id + ")");
        }
        ProductCategory productCategory = mapper.toEntity(dto);
        productCategory = repository.save(productCategory);
        return Optional.of(mapper.toDto(productCategory));
    }

    @Override
    public Optional<ProductCategoryDTO> partialUpdate(ProductCategoryDTO dto) {
        log.debug("Request to partially update ProductCategory : {}", dto);

        return repository
                .findById(dto.getId())
                .map(existing -> {
                    mapper.partialUpdate(existing, dto);
                    return existing;
                })
                .map(repository::save)
                .map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductCategories");
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductCategoryDTO> findOne(Long id) {
        log.debug("Request to get ProductCategory : {}", id);
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductCategory : {}", id);
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete; category not found (id=" + id + ")");
        }
        repository.deleteById(id);
    }
}
