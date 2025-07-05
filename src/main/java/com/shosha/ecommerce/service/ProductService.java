package com.shosha.ecommerce.service;

import com.shosha.ecommerce.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {

    ProductDTO save(ProductDTO productDTO);

    Optional<ProductDTO> update(ProductDTO productDTO);

    Page<ProductDTO> findAll(Pageable pageable);

    Optional<ProductDTO> findOne(Long id);

    void delete(Long id);

    Optional<ProductDTO> partialUpdate(ProductDTO productDTO);
}

