package com.shosha.ecommerce.controller;

import com.shosha.ecommerce.dto.ProductCategoryDTO;
import com.shosha.ecommerce.dto.ProductDTO;
import com.shosha.ecommerce.service.ProductCategoryService;
import com.shosha.ecommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final ProductService productService;
    private final ProductCategoryService productCategoryService;

    private final String PRODUCT_URL = "/api/products";
    private final String PRODUCT_CATEGORY_URL = "/api/category";


    public InventoryController(ProductService productService,
                               ProductCategoryService productCategoryService) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }


    @GetMapping("/products")
    public ResponseEntity<Page<ProductDTO>> getAllProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) throws URISyntaxException {
        return ResponseEntity.created(new URI(PRODUCT_URL)).body(productService.save(productDTO));
    }

    @PutMapping("/products")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.update(productDTO).orElse(null));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories")
    public ResponseEntity<Page<ProductCategoryDTO>> getAllCategories(Pageable pageable) {
        return ResponseEntity.ok(productCategoryService.findAll(pageable));
    }

    @PostMapping("/categories")
    public ResponseEntity<ProductCategoryDTO> addCategory(@RequestBody ProductCategoryDTO productCategoryDTO) throws URISyntaxException {
        return ResponseEntity.created(new URI(PRODUCT_CATEGORY_URL)).body(productCategoryService.save(productCategoryDTO));
    }

    @PutMapping("/categories")
    public ResponseEntity<ProductCategoryDTO> updateCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
        return ResponseEntity.ok(productCategoryService.update(productCategoryDTO).orElse(null));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<ProductCategoryDTO> deleteCategory(@PathVariable Long id) {
        productCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
