package com.example.atomictype.Presentation;

import com.example.atomictype.Business.Entity.RaceState;
import com.example.atomictype.Business.Entity.UserState;
import com.example.atomictype.Business.Service.RaceStateService;
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

import java.util.ArrayList;

@Controller
public class RaceController {

    RaceStateService raceService;
    AsyncController asyncController;

    @Autowired
    public RaceController(RaceStateService raceService, AsyncController asyncController) {
        this.raceService = raceService;
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
    public String joinRace(Model model, @PathVariable Long raceId){
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
    public String homePage(Model model){
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

}
