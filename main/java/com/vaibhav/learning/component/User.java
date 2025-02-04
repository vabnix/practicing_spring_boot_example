package com.vaibhav.learning.component;

import org.springframework.stereotype.Component;

@Component
public class User {

    Order order;

    public User(Order order){
        this.order = order;
        System.out.println("Initializing User");
    }

}
