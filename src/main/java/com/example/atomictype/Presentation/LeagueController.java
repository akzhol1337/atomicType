package com.example.atomictype.Presentation;

import com.example.atomictype.Business.Entity.League;
import com.example.atomictype.Business.Service.LeagueService;
import com.example.atomictype.Business.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeagueController {
    UserService userService;
    LeagueService leagueService;

    @Autowired
    public LeagueController(UserService userService, LeagueService leagueService) {
        this.userService = userService;
        this.leagueService = leagueService;
    }

    @PostMapping("/createLeague")
    public boolean createLeague(@RequestBody League league){
        if(leagueService.existsByName(league.getName())){
            return false;
        }
        leagueService.save(league);
        return true;
    }
}
