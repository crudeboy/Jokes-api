//package com.jazz.jokes.services;
//
//import jakarta.annotation.PostConstruct;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class User {
//
//    @Autowired
//    @Lazy
//    Order order;
//
//    public User(){
//        log.info("user initialized");
//        log.info("order in constructor {}", order);
//    }
//
//    @PostConstruct
//    public void checkDependencies() {
//        log.info("User bean dependencies resolved.");
//        log.info("Is 'order' bean initialized? {}", order != null);
//        log.info("Is 'order' bean initialized? {}", order.toString());
//    }
//}
