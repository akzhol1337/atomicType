package com.example.atomictype.Presentation;

import com.example.atomictype.Business.Entity.League;
import com.example.atomictype.Business.Entity.User;
import com.example.atomictype.Business.Service.LeagueService;
import com.example.atomictype.Business.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.security.Principal;

@RestController @Transactional
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
                                                @RequestParam String country,
                                                Principal principal){

        League league = new League(name, description, password, slots, min_average_wpm, full_or_last10, country);
        if(leagueService.existsByName(league.getName())){
            return ResponseEntity.ok(false);
        }
        String loggedUsername = principal.getName();
        User user = userService.getUser(loggedUsername);

        league.getAdmins().add(user);

        leagueService.save(league);

        user.getLeagues().add(league);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/joinleague/{leagueName}")
    public ResponseEntity<Boolean> joinLeague(@PathVariable String leagueName, Principal principal){
        String loggedUsername = principal.getName();
        League league = leagueService.findByName(leagueName);
        User user = userService.getUser(loggedUsername);


        System.out.println(user.getUsername());

        league.getUsers().add(user);
        user.getLeagues().add(league);

        return ResponseEntity.ok(true);
    }
}
