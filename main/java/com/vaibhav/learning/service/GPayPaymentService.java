package com.vaibhav.learning.service;

public class GPayPaymentService implements PaymentService {

    @Override
    public void processPayment(double amount) {
        System.out.println("GPay Payment is being processed");
    }
}
