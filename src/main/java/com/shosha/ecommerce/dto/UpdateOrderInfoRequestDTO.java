package com.shosha.ecommerce.dto;

import java.io.Serializable;

public class UpdateOrderInfoRequestDTO implements Serializable {

    private Long orderId;
    private AddressDTO shippingAddress;
    private AddressDTO pillingAddress;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public AddressDTO getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(AddressDTO shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public AddressDTO getPillingAddress() {
        return pillingAddress;
    }

    public void setPillingAddress(AddressDTO pillingAddress) {
        this.pillingAddress = pillingAddress;
    }
}
