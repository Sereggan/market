package com.nikolaychuks.orderservice.enums;

import lombok.*;

public enum OrderStatus {
    ORDER_CREATED("order created"),
    ORDER_CONFIRMED("order confirmed"),
    ORDER_COMPLETED("order completed");

    private final String displayValue;

    private OrderStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    /**
     * Gets display value.
     *
     * @return the display value
     */
    public String getDisplayValue() {
        return displayValue;
    }
}
