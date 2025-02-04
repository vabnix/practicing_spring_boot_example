package com.vaibhav.learning.config;

import com.vaibhav.learning.service.CreditCardPaymentService;
import com.vaibhav.learning.service.GPayPaymentService;
import com.vaibhav.learning.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {

    @Bean
    public PaymentService creditCardPayment(){
        return new CreditCardPaymentService();
    }

    @Bean
    public PaymentService gPayPayment(){
        return new GPayPaymentService();
    }
}
