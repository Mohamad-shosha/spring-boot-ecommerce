package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dao.CanceledOrderRepository;
import com.shosha.ecommerce.dto.CanceledOrderDTO;
import com.shosha.ecommerce.entity.CanceledOrder;
import com.shosha.ecommerce.service.CanceledOrderService;
import com.shosha.ecommerce.service.mapper.CanceledOrderMapper;
import com.shosha.ecommerce.service.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CanceledOrderServiceImpl implements CanceledOrderService {

    private final CanceledOrderRepository canceledOrderRepository;
    private final CanceledOrderMapper canceledOrderMapper;

    public CanceledOrderServiceImpl(CanceledOrderRepository canceledOrderRepository,
                                    CanceledOrderMapper canceledOrderMapper) {
        this.canceledOrderRepository = canceledOrderRepository;
        this.canceledOrderMapper = canceledOrderMapper;
    }

    @Override
    public CanceledOrderDTO save(CanceledOrderDTO canceledOrderDTO) {
        CanceledOrder canceledOrder = canceledOrderMapper.toEntity(canceledOrderDTO);
        canceledOrderRepository.save(canceledOrder);
        return canceledOrderDTO;
    }

    @Override
    public Optional<CanceledOrderDTO> findOne(Long id) {
        return canceledOrderRepository.findById(id).map(canceledOrderMapper::toDto);
    }

    @Override
    public Optional<CanceledOrderDTO> findByOrderId(Long id) {
        return canceledOrderRepository.findByOrderId(id).map(canceledOrderMapper::toDto);
    }

    @Override
    public List<CanceledOrderDTO> findAll() {
        return canceledOrderRepository.findAll().stream().map(canceledOrderMapper::toDto).toList();
    }

    @Override
    public List<CanceledOrderDTO> findByCustomerId() {
        Long customerId = SecurityUtil.getCurrentUser().getId();
        return canceledOrderRepository.findByUserId(customerId).stream().map(canceledOrderMapper::toDto).toList();
    }

    @Override
    public void delete(Long id) {
        if (!canceledOrderRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete; order not found (id=" + id + ")");
        }
        canceledOrderRepository.deleteById(id);
    }

}
