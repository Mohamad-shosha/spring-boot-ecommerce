package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dao.FAQsRepository;
import com.shosha.ecommerce.dto.FAQsDTO;
import com.shosha.ecommerce.entity.FAQs;
import com.shosha.ecommerce.service.FAQsService;
import com.shosha.ecommerce.service.mapper.FAQsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FAQsServiceImpl implements FAQsService {

    private final Logger log = LoggerFactory.getLogger(FAQsServiceImpl.class);

    private final FAQsRepository faqsRepository;
    private final FAQsMapper faqsMapper;

    public FAQsServiceImpl(FAQsRepository faqsRepository,
                           FAQsMapper faqsMapper) {
        this.faqsRepository = faqsRepository;
        this.faqsMapper = faqsMapper;
    }

    @Override
    public FAQsDTO save(FAQsDTO faqsDTO) {
        log.debug("Request to save FAQs : {}", faqsDTO);
        FAQs faqs = faqsMapper.toEntity(faqsDTO);
        faqs = faqsRepository.save(faqs);
        return faqsMapper.toDto(faqs);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FAQsDTO> findAll() {
        log.debug("Request to get all FAQs");
        return faqsRepository.findAll().stream().map(faqsMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FAQsDTO> findOne(Integer id) {
        log.debug("Request to get FAQs : {}", id);
        return faqsRepository.findById(id).map(faqsMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete FAQs : {}", id);
        faqsRepository.deleteById(id);
    }
}
