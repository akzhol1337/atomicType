package com.example.atomictype.Presentation;

import com.example.atomictype.Business.Entity.RaceState;
import com.example.atomictype.Business.Entity.User;
import com.example.atomictype.Business.Entity.UserState;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

@Controller
public class RaceController {

    RaceState raceState = null;

    @MessageMapping("/{raceId}")
    @SendTo("/topic/{raceId}")
    public RaceState raceState(UserState userState){
        if(Objects.equals(userState.getMethod(), "JOIN")){
            raceState.setMethod("JOINED");
            raceState.setLastPlayer(userState.getName());
            return raceState;
        }
        return null;
    }



    @GetMapping("/race/{raceId}")
    public String joinRace(Model model, @PathVariable Long raceId){
        model.addAttribute("raceLink", "http://localhost:8080/race/" + raceId);
        if(raceState == null){
            raceState = new RaceState("CREATE", raceId, new ArrayList<>(), "none");
        }
        return "game";
    }


    @GetMapping("/")
    public String homePage(Model model){
        return "home";
    }

}
