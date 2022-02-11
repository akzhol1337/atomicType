package com.example.atomictype.Persistance.Repository;

import com.example.atomictype.Business.Entity.League;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends CrudRepository<League, Long> {

    boolean existsLeagueByName(String name);
}
