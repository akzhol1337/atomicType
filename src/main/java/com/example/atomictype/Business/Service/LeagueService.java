package com.example.atomictype.Business.Service;

import com.example.atomictype.Business.Entity.League;
import com.example.atomictype.Persistance.Repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeagueService {
    LeagueRepository leagueRepo;

    @Autowired
    public LeagueService(LeagueRepository leagueRepo) {
        this.leagueRepo = leagueRepo;
    }

    public void save(League league){
        leagueRepo.save(league);
    }

    public boolean existsByName(String name){
        return leagueRepo.existsLeagueByName(name);
    }

    public League findByName(String name){
        return leagueRepo.findByName(name);
    }
}
