package com.shosha.ecommerce.dao;

import com.shosha.ecommerce.entity.FAQs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQsRepository extends JpaRepository<FAQs, Integer> {
}
