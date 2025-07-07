package com.shosha.ecommerce.dao;

import com.shosha.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.stream.Stream;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {
/*
http://localhost:8080/api/products/search/findByCategoryId?id=1&page=0&size=5
GET http://localhost:8080/api/products/search/findByNameContaining?name=Crash%20Course%20in%20Python&page=0&size=5
 */
    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);

    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);

    @Query("select p from Product p")
    Stream<Product> streamAll();
}
