package com.shosha.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class NlpPushResponseDTO implements Serializable {
    private String signal;

    @JsonProperty("inserted_items_count")
    private Long insertedItemsCount;

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public Long getInsertedItemsCount() {
        return insertedItemsCount;
    }

    public void setInsertedItemsCount(Long insertedItemsCount) {
        this.insertedItemsCount = insertedItemsCount;
    }
}
