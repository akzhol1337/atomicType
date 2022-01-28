package com.example.atomictype.Presentation;

import com.example.atomictype.Business.Entity.UserState;
import com.example.atomictype.Business.Service.RaceStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    RaceStateService raceService;

    @Autowired
    public RestController(RaceStateService raceService) {
        this.raceService = raceService;
    }

    @GetMapping("/loadUsers/{raceId}")
    public List<UserState> loadUsers(@PathVariable Long raceId){
        return raceService.getAllPlayers(raceId);
    }
}
