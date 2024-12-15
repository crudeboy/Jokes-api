package com.jazz.jokes.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledSending {

    private final Twilo twilo;

    @Scheduled(cron = "0 0 * * * *")
    void sendJoke(){
        twilo.sendMessage();
    }
}
