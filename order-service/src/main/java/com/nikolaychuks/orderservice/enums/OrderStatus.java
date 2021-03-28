package com.nikolaychuks.orderservice.enums;

public enum OrderStatus {
    ORDER_CREATED("order created"),
    ORDER_REJECTED("order rejected"),
    ORDER_CONFIRMED("order confirmed"),
    ORDER_RESERVED("order reserved"),
    ORDER_COMPLETED("order completed"),
    ORDER_CANCELLED("order cancelled");

    private final String displayValue;

    OrderStatus(String displayValue) {
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
