package com.seaborne.order_validator.service;

import com.seaborne.consumervalidator.SendOrderRequest;

public interface OrderService{
    public Boolean isOrderValid(SendOrderRequest orderRequest);
}
