package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dao.FAQsRepository;
import com.shosha.ecommerce.dto.FAQsDTO;
import com.shosha.ecommerce.dto.OrderDTO;
import com.shosha.ecommerce.dto.UserDTO;
import com.shosha.ecommerce.entity.FAQs;
import com.shosha.ecommerce.entity.enums.OrderStatus;
import com.shosha.ecommerce.service.FAQsService;
import com.shosha.ecommerce.service.OrderService;
import com.shosha.ecommerce.service.OrderStatusMessageConstants;
import com.shosha.ecommerce.service.mapper.FAQsMapper;
import com.shosha.ecommerce.service.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final OrderService orderService;

    public FAQsServiceImpl(FAQsRepository faqsRepository,
                            FAQsMapper faqsMapper,
                           OrderService orderService) {
        this.faqsRepository = faqsRepository;
        this.faqsMapper = faqsMapper;
        this.orderService = orderService;
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
        if(id == 13 || id == 14) {
            return Optional.of(buildOrderSummaryAnswerForUser(id));
        }
        return faqsRepository.findById(id).map(faqsMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete FAQs : {}", id);
        faqsRepository.deleteById(id);
    }

    public FAQsDTO buildOrderSummaryAnswerForUser(Integer questionId) {
        FAQsDTO faqsDTO = new FAQsDTO();
        faqsDTO.setId(questionId);

        UserDTO userDTO = SecurityUtil.getCurrentUser();
        if (userDTO == null) {
            faqsDTO.setAnswer("You need to be logged in to access this information. Please log in and try again.");
            return faqsDTO;
        }

        List<OrderDTO> userOrders = getUserOrders(userDTO.getId());
        if (userOrders == null || userOrders.isEmpty()) {
            faqsDTO.setAnswer("You don't have any orders at the moment.");
            return faqsDTO;
        }

        StringBuilder answer = new StringBuilder();
        answer.append("<div style='font-family: Arial, sans-serif;'>")
                .append("<h3>Summary of Your Recent Orders</h3>")
                .append("<ul style='padding-left: 1.2em;'>");

        for (OrderDTO orderDTO : userOrders) {
            String message = getOrderStatusMessage(orderDTO.getStatus());
            answer.append("<li>")
                    .append("<strong>Order:</strong> ")
                    .append(orderDTO.getOrderTrackingNumber())
                    .append("<br/>")
                    .append("<strong>Status:</strong> ")
                    .append(message)
                    .append("</li>");
        }

        answer.append("</ul>")
                .append("</div>");

        faqsDTO.setAnswer(answer.toString());

        return faqsDTO;
    }


    private List<OrderDTO> getUserOrders(Long userId) {
        return orderService.getCustomerOrders(userId);
    }

    private String getOrderStatusMessage(String orderStatus) {
        if(orderStatus.equals(OrderStatus.PENDING.name())) {
            return OrderStatusMessageConstants.PENDING_ORDER_MESSAGE;
        }
        else if(orderStatus.equals(OrderStatus.CONFIRMED.name())) {
            return OrderStatusMessageConstants.APPROVED_ORDER_MESSAGE;
        }
        else if(orderStatus.equals(OrderStatus.PROCESSING.name())) {
            return OrderStatusMessageConstants.PROCESSING_ORDER_MESSAGE;
        }
        else if(orderStatus.equals(OrderStatus.SHIPPED.name())) {
            return OrderStatusMessageConstants.SHIPPED_ORDER_MESSAGE;
        }
        else if(orderStatus.equals(OrderStatus.CANCELLED.name())) {
            return OrderStatusMessageConstants.CANCELED_ORDER_MESSAGE;
        }
        else {
            return OrderStatusMessageConstants.DEFAULT_ORDER_MESSAGE;
        }
    }
}
