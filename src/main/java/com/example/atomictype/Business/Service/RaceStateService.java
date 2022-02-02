package com.example.atomictype.Business.Service;

import com.example.atomictype.Business.Entity.RaceState;
import com.example.atomictype.Business.Entity.UserState;
import com.example.atomictype.Persistance.Repository.RaceRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.websocket.Session;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RaceStateService {
    ConcurrentHashMap<Long, RaceState> raceStateMap;

    @Autowired
    public RaceStateService(ConcurrentHashMap<Long, RaceState> raceStateMap) {
        this.raceStateMap = raceStateMap;
    }

    public RaceState createRace(RaceState raceState){
        return raceStateMap.put(raceState.getGameId(), raceState);
    }

    public Long getQuoteId(Long raceId){
        return raceStateMap.get(raceId).getQuoteId();
    }

    public RaceState addPlayer(UserState userState){
        RaceState raceState = raceStateMap.get(userState.getRaceId());
        raceState.addPlayer(userState);
        raceState.setMethod("JOIN");
        raceState.setLastPlayer(userState);
        return raceState;
    }

    public RaceState startRace(Long raceId){
        RaceState raceState = raceStateMap.get(raceId);
        raceState.setMethod("START");
        raceState.setStarted(true);
        raceState.setLastPlayer(null);
        return raceState;
    }

    public RaceState updatePlayer(UserState userState){
        RaceState raceState = raceStateMap.get(userState.getRaceId());
        List<UserState> users = raceState.getPlayers();
        for(int i = 0; i < users.size(); i++){
            if(Objects.equals(users.get(i).getUserId(), userState.getUserId())){
                users.set(i, userState);
            }
        }
        raceState.setMethod("PLAY");
        raceState.setLastPlayer(userState);
        return raceState;
    }

    public List<UserState> getAllPlayers(Long raceId){
        if(raceStateMap.containsKey(raceId)) {
            return raceStateMap.get(raceId).getPlayers();
        }
        return null;
    }
}
