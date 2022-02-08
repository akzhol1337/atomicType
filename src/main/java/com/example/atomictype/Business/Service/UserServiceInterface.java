package com.example.atomictype.Business.Service;

import com.example.atomictype.Business.Entity.AUser;

import java.util.List;

public interface UserServiceInterface {
    AUser saveUser(AUser AUser);
    AUser getUser(String username);
    boolean hasUser(String username);
    boolean hasEmail(String email);
    void addFriend(String username, String friendName);
    AUser getUserByUsername(String username);
    List<AUser> getUsers();
}

