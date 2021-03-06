package com.example.atomictype.Presentation;

import com.example.atomictype.Business.Entity.RaceState;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;

@Controller
@EnableAsync
public class AsyncController {

    private SimpMessagingTemplate template;

    public AsyncController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Async
    public void startRace(RaceState raceState, Long raceId) throws InterruptedException {
        raceState.setLastPlayer(null);
        for(int i = 5; i >= 0; i--){
            Thread.sleep(1000);
            raceState.setMethod("START" + ((i != 0) ? (" " + i) : ""));
            template.convertAndSend("/topic/" + raceId, raceState);
        }
    }
}
