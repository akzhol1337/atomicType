package com.example.atomictype.Presentation;

import com.example.atomictype.Business.Entity.*;
import com.example.atomictype.Business.Service.QuoteService;
import com.example.atomictype.Business.Service.RaceStateService;
import com.example.atomictype.Business.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@org.springframework.web.bind.annotation.RestController
@Transactional
public class RestController {

    RaceStateService raceService;
    QuoteService quoteService;
    UserService userService;

    @Autowired
    public RestController(RaceStateService raceService, QuoteService quoteService, UserService userService) {
        this.raceService = raceService;
        this.quoteService = quoteService;
        this.userService = userService;
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

    @PostMapping("/api/follow")
    public void followUser(@RequestParam String username, Principal principal){
        String loggedUsername = principal.getName();
        userService.followUser(username, loggedUsername);
    }

    @GetMapping("/api/getCredentials")
    public ResponseEntity<User> getCredentials(Principal principal){
        if(principal == null){
            return ResponseEntity.ok(null);
        } else {
            String loggedUsername = principal.getName();
            User user = userService.getUser(loggedUsername);
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping("/api/updateStatistics")
    public void updateStatistics(@RequestParam Long quoteId, @RequestParam Integer wpm, @RequestParam Integer place, @RequestParam Float accuracy, Principal principal){
        String loggedUsername = principal.getName();

        User user = userService.getUser(loggedUsername);
        if(user == null) {
            return;
        }
        user.setAverage_wpm_full((user.getAverage_wpm_full() * user.getNumber_of_races() + wpm) / (user.getNumber_of_races() + 1));
        if(wpm > user.getBest_wpm()){
            user.setBest_wpm(wpm);
        }
        List<RaceResults> races = user.getRacesHistory();
        Quote quote = quoteService.getQuoteById(quoteId);

        int sum = 0;
        if(races.size() >= 10) {
            for (int i = races.size() - 1; i >= races.size() - 9; i--) {
                List<UserResult> userResult = races.get(i).getUsers();
                for (UserResult to : userResult) {
                    if (Objects.equals(to.getUsername(), user.getUsername())) {
                        sum += to.getSpeed();
                    }
                }
            }
        } else {
            sum = user.getAverage_wpm_full() * user.getNumber_of_races();
        }
        sum += wpm;
        int last10_avg_wpm = sum / 10;

        if(place == 1){
            user.setRaces_won(user.getRaces_won() + 1);
        }

        user.setNumber_of_races(user.getNumber_of_races() + 1);
        user.setExperience_points(user.getExperience_points() + quote.getContent().length()/10);
        user.setAverage_wpm_last10(last10_avg_wpm);
    }
}
