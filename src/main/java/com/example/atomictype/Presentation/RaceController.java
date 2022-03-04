package com.example.atomictype.Presentation;

import com.example.atomictype.Business.Entity.RaceState;
import com.example.atomictype.Business.Entity.User;
import com.example.atomictype.Business.Entity.UserState;
import com.example.atomictype.Business.Service.RaceStateService;
import com.example.atomictype.Business.Service.UserService;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class RaceController {

    RaceStateService raceService;
    UserService userService;
    AsyncController asyncController;

    @Autowired
    public RaceController(RaceStateService raceService, UserService userService, AsyncController asyncController) {
        this.raceService = raceService;
        this.userService = userService;
        this.asyncController = asyncController;
    }

    @MessageMapping("/play/{raceId}")
    @SendTo("/topic/{raceId}")
    public RaceState playRace(UserState userState) {
        return raceService.updatePlayer(userState);
    }

    @MessageMapping("/join/{raceId}")
    @SendTo("/topic/{raceId}")
    public RaceState joinRace(UserState userState, @DestinationVariable Long raceId) throws InterruptedException {

        RaceState raceState = raceService.addPlayer(userState);

        if (raceState.getPlayers().size() >= 2 && !raceState.isStarted()){
            asyncController.startRace(raceState, raceId);
        }

        return raceState;
    }

    @GetMapping("/race/{raceId}")
    public String joinRace(Model model, @PathVariable Long raceId, Principal principal){
        if(principal == null){
            model.addAttribute("username", null);
            model.addAttribute("userId", null);
        } else {
            String username = principal.getName();
            User user = userService.getUser(username);
            model.addAttribute("username", username);
            model.addAttribute("userId", user.getId());
        }
        model.addAttribute("raceLink", "http://localhost:8080/race/" + raceId);
        model.addAttribute("quoteId", raceService.getQuoteId(raceId));
        return "game";
    }

    @PostMapping("/createRace/{raceId}/{quoteId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void createRace(@PathVariable Long raceId, @PathVariable Long quoteId){
        RaceState raceState = new RaceState("CREATE", raceId, quoteId, new ArrayList<UserState>(), null, false);
        System.out.println(raceState.getGameId() + " | " + raceState.getQuoteId());
        raceService.createRace(raceState);
    }

    @GetMapping("/")
    public String homePage(Model model, Principal principal){
        boolean authenticated = false;
        String username = null;
        if(principal != null){
            authenticated = true;
            username = principal.getName();
        }
        model.addAttribute("authenticated", authenticated);
        model.addAttribute("username", username);
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/leaderboard")
    public String leaderboard(Model model, Principal principal) {
        boolean authenticated = false;
        User user = null;
        if(principal != null){
            authenticated = true;
            user = userService.getUser(principal.getName());
        }
        model.addAttribute("auth", authenticated);

        class leaderboardUser {
            String place;
            String username;
            Integer speed;

            public leaderboardUser(String place, String username, Integer speed) {
                this.place = place;
                this.username = username;
                this.speed = speed;
            }

            public String getPlace() {
                return place;
            }

            public void setPlace(String place) {
                this.place = place;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public Integer getSpeed() {
                return speed;
            }

            public void setSpeed(Integer speed) {
                this.speed = speed;
            }
        }

        List<leaderboardUser> top5 = new ArrayList<>();

        if(user != null && !user.getFollowings().isEmpty()){
            List<User> users = user.getFollowings();

            for(int i = 0; i < users.size()-1; i++){
                for(int j = 0; j < users.size() - i - 1; j++){
                    if(users.get(j).getAverage_wpm_full() < users.get(j+1).getAverage_wpm_full()){
                        User tempUser = users.get(j);
                        users.set(j, users.get(j+1));
                        users.set(j+1, tempUser);
                    }
                }
            }
            for(int i = 0; i < Math.min(5, users.size()); i++){
                top5.add(new leaderboardUser("#"+(i+1), users.get(i).getUsername(), users.get(i).getAverage_wpm_full()));
            }
        }
        model.addAttribute("top5", top5);
        return "leaderboard";
    }

}
