package com.jazz.jokes.services;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.math.BigDecimal;

@Component @Slf4j
public class Twilo {
    // Find your Account Sid and Token at twilio.com/console

    @Autowired
    TwiloConfigProperties twiloConfigProperties;

    @PostConstruct
    void init(){
        log.info("getAccountSid {}", twiloConfigProperties.getAccountSid());
        log.info("getAuthToken {}", twiloConfigProperties.getAuthToken());
    }

    public void sendMessage() {
        Twilio.init(twiloConfigProperties.getAccountSid(), twiloConfigProperties.getAuthToken());
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("whatsapp:+2348148882784"), //2348148882784
                        new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                        JokesIntegration.readAJoke())
                .create();

        System.out.println(message.getSid());
    }
}