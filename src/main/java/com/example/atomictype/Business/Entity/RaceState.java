package com.example.atomictype.Business.Entity;

import java.util.ArrayList;

public class RaceState {
    private String method;
    private long gameId;
    private ArrayList<UserState> players;
    private String lastPlayer;

    public RaceState(String method, long gameId, ArrayList<UserState> players, String lastPlayer) {
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

    public ArrayList<UserState> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<UserState> players) {
        this.players = players;
    }

    public String getLastPlayer() {
        return lastPlayer;
    }

    public void setLastPlayer(String lastPlayer) {
        this.lastPlayer = lastPlayer;
    }
}
