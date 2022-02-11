package com.example.atomictype.Presentation;

import com.example.atomictype.Business.Entity.League;
import com.example.atomictype.Business.Service.LeagueService;
import com.example.atomictype.Business.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Boolean> createLeague(@RequestParam String name,
                                                @RequestParam String description,
                                                @RequestParam String password,
                                                @RequestParam Integer slots,
                                                @RequestParam Integer min_average_wpm,
                                                @RequestParam Boolean full_or_last10,
                                                @RequestParam String country
                                                ){

        League league = new League(name, description, password, slots, min_average_wpm, full_or_last10, country);
        if(leagueService.existsByName(league.getName())){
            System.out.println("haha");
            return ResponseEntity.ok(false);
        }
        leagueService.save(league);
        return ResponseEntity.ok(true);
    }
}
