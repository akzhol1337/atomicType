package com.example.atomictype.Business.Config;

import com.example.atomictype.Business.Entity.RaceState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class DataStructuresBeanConfig {
    @Bean
    public ConcurrentHashMap<Long, RaceState> getHashMap(){
        return new ConcurrentHashMap<>();
    }
}
