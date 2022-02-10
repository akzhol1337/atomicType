package com.example.atomictype.Business.Service;

import com.example.atomictype.Business.Entity.Role;
import com.example.atomictype.Business.Entity.User;

import java.util.List;

public interface UserServiceInterface {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    boolean hasUser(String username);
    boolean hasEmail(String email);
    void addFriend(String username, String friendName);
    User getUserByUsername(String username);
    List<User> getUsers();
}

