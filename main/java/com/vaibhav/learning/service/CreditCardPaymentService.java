package com.vaibhav.learning.service;

public class CreditCardPaymentService implements PaymentService {

    @Override
    public void processPayment(double amount) {
        System.out.println("Credit Card Payment is being processed");
    }
}
