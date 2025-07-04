package com.shosha.ecommerce.entity.enums;

public enum OrderStatus {
    PENDING,         // Order created but not confirmed yet
    CONFIRMED,       // Payment confirmed
    PROCESSING,      // Order being prepared/packed
    SHIPPED,         // Order has been handed to courier
    DELIVERED,       // Order successfully delivered
    CANCELLED,       // Cancelled by customer or system
    RETURN_REQUESTED,// Customer requested a return
    RETURNED,        // Order returned and received
    REFUNDED,        // Refund processed
    FAILED           // Payment or processing failure
}
