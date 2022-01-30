package com.example.atomictype.Persistance.Repository;

import com.example.atomictype.Business.Entity.Quote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {
}
