package com.shosha.ecommerce.service;


import com.shosha.ecommerce.dto.CanceledOrderDTO;

import java.util.List;
import java.util.Optional;

public interface CanceledOrderService {
    CanceledOrderDTO save(CanceledOrderDTO canceledOrderDTO);
    Optional<CanceledOrderDTO> findOne(Long id);
    Optional<CanceledOrderDTO> findByOrderId(Long id);
    List<CanceledOrderDTO> findAll();
    List<CanceledOrderDTO> findByCustomerId();
    void delete(Long id);
}
