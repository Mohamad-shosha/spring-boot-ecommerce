package com.shosha.ecommerce.service;

import com.shosha.ecommerce.dto.Purchase;
import com.shosha.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
