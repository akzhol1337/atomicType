package com.example.atomictype.Business.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class RaceState {
    private String method;
    private long gameId;
    private List<UserState> players = new ArrayList<>();
    private UserState lastPlayer;

    public RaceState(String method, long gameId, ArrayList<UserState> players, UserState lastPlayer) {
        this.method = method;
        this.gameId = gameId;
        this.players = players;
        this.lastPlayer = lastPlayer;
    }

    public RaceState() {
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public List<UserState> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<UserState> players) {
        this.players = players;
    }

    public void addPlayer(UserState userState){
        players.add(userState);
    }

    public UserState getLastPlayer() {
        return lastPlayer;
    }

    public void setLastPlayer(UserState lastPlayer) {
        this.lastPlayer = lastPlayer;
    }
}
