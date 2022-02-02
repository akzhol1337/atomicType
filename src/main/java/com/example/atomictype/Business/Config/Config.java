package com.example.atomictype.Business.Config;

import com.example.atomictype.Business.Entity.RaceState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class Config {

    @Bean
    public ConcurrentHashMap<Long, RaceState> getHashMap(){
        return new ConcurrentHashMap<>();
    }
}
