package com.vaibhav.learning.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Order {

    User user;

    @Autowired
    @Lazy
    public Order(User user){
        this.user = user;  //Creating User dependency in Order
        System.out.println("Initializing Order");
    }

    public void process(){
        System.out.println("Processing Order");
    }
}
