package com.jazz.jokes.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "joker.twilo")
@Getter
@Setter
@NoArgsConstructor
public class TwiloConfigProperties {

    private String accountSid;
    private String authToken;
}