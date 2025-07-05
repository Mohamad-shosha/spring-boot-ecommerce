package com.shosha.ecommerce.service;

import com.shosha.ecommerce.dto.ProductCategoryDTO;
import com.shosha.ecommerce.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductCategoryService {

    ProductCategoryDTO save(ProductCategoryDTO dto);

    Optional<ProductCategoryDTO> update(ProductCategoryDTO dto);

    Optional<ProductCategoryDTO> partialUpdate(ProductCategoryDTO dto);

    Page<ProductCategoryDTO> findAll(Pageable pageable);

    Optional<ProductCategoryDTO> findOne(Long id);

    void delete(Long id);
}

