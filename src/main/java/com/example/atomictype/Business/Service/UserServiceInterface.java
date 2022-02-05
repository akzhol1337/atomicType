package com.example.atomictype.Business.Service;

import com.example.atomictype.Business.Entity.AUser;

import java.util.List;

public interface UserServiceInterface {
    AUser saveUser(AUser AUser);
    AUser getUser(String username);
    void addFriend(String username, String friendName);
    List<AUser> getUsers();
}

