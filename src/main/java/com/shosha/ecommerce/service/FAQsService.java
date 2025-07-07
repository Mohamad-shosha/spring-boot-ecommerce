package com.shosha.ecommerce.service;

import com.shosha.ecommerce.dto.FAQsDTO;

import java.util.List;
import java.util.Optional;

public interface FAQsService {
    FAQsDTO save(FAQsDTO faqsDTO);

    List<FAQsDTO> findAll();

    Optional<FAQsDTO> findOne(Integer id);

    void delete(Integer id);
}
