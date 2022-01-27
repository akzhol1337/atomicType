package com.example.atomictype.Business.Entity;

public class UserState {
    private String method;
    private Long userId;
    private String name;

    public UserState(String method, Long userId, String name) {
        this.method = method;
        this.userId = userId;
        this.name = name;
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
}
