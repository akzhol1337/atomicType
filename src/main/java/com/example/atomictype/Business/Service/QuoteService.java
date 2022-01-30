package com.example.atomictype.Business.Service;

import com.example.atomictype.Business.Entity.Quote;
import com.example.atomictype.Persistance.Repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class QuoteService {
    QuoteRepository repo;
    int numberOfQuotesInDatabase = 5;
    Random random = new Random();

    @Autowired
    public QuoteService(QuoteRepository repo) {
        this.repo = repo;
    }

    public Quote getRandomQuote(){
        Long quoteId = random.nextLong(0, numberOfQuotesInDatabase-1);
        return repo.findById(quoteId).orElse(null);
    }

    public Quote getQuoteById(Long id){
        return repo.findById(id).orElse(null);
    }

    public Long getRandomQuoteId(){
        return random.nextLong(0, numberOfQuotesInDatabase-1);
    }
}
