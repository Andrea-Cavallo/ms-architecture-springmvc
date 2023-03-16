package com.application.payment.controller.dto;
public enum Currency {
    USD("US Dollar"),
    EUR("Euro"),
    GBP("British Pound Sterling"),
    JPY("Japanese Yen"),
    AUD("Australian Dollar"),
    CAD("Canadian Dollar"),
    CHF("Swiss Franc"),
    CNY("Chinese Yuan"),
    SEK("Swedish Krona"),
    NZD("New Zealand Dollar");

    private final String fullName;

    Currency(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
