package com.shosha.ecommerce.service.cron;

import com.shosha.ecommerce.dao.OrderRepository;
import com.shosha.ecommerce.entity.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class OrderCronJob {
    Logger logger = LoggerFactory.getLogger(OrderCronJob.class);

    private final OrderRepository orderRepository;

    public OrderCronJob(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    @Scheduled(cron = "0 0 * * * ?")
    public void processAllConfirmedOrders() {
        logger.info("process all orders CronJob started");
        orderRepository.updateStatusByPrevStatus(OrderStatus.CONFIRMED.name(), OrderStatus.PROCESSING.name());
    }

    @Scheduled(cron = "0 0 11 * * ?")
    public void shipAllProcessingOrders() {
        logger.info("ship all Orders CronJob started");
        orderRepository.updateStatusByPrevStatus(OrderStatus.PROCESSING.name(), OrderStatus.SHIPPED.name());
    }
}
