package com.example.atomictype.Business.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class UserState {
    private String method;
    private Long userId;
    private Long raceId;
    private String name;
    private Integer speed;
    private Integer progress;

    public UserState(String method, Long userId, Long raceId, String name, Integer speed, Integer progress) {
        this.method = method;
        this.userId = userId;
        this.raceId = raceId;
        this.name = name;
        this.speed = speed;
        this.progress = progress;
    }

    public UserState() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Long getRaceId() {
        return raceId;
    }

    public void setRaceId(Long raceId) {
        this.raceId = raceId;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}
