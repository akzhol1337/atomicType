package com.example.atomictype.Presentation;

import com.example.atomictype.Business.Entity.Quote;
import com.example.atomictype.Business.Entity.UserState;
import com.example.atomictype.Business.Service.QuoteService;
import com.example.atomictype.Business.Service.RaceStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    RaceStateService raceService;
    QuoteService quoteService;

    @Autowired
    public RestController(RaceStateService raceService, QuoteService quoteService) {
        this.raceService = raceService;
        this.quoteService = quoteService;
    }

    @GetMapping("/loadUsers/{raceId}")
    public List<UserState> loadUsers(@PathVariable Long raceId){
        return raceService.getAllPlayers(raceId);
    }

    @GetMapping("/api/randomquote")
    public Quote getRandomQuote(){
        return quoteService.getRandomQuote();
    }

    @GetMapping("/api/quote/{quoteId}")
    public Quote getQuoteById(@PathVariable Long quoteId){
        return quoteService.getQuoteById(quoteId);
    }

    @GetMapping("/api/randomquoteid")
    public Long getRandomQuoteId(){
        return quoteService.getRandomQuoteId();
    }
}
