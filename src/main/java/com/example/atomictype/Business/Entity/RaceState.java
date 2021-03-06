package com.example.atomictype.Business.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class RaceState {
    private String method;
    private long gameId;
    private long quoteId;
    private List<UserState> players = new ArrayList<>();
    private UserState lastPlayer;
    private boolean started;

    public long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(long quoteId) {
        this.quoteId = quoteId;
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

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
