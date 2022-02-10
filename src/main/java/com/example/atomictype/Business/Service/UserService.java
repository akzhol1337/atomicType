package com.example.atomictype.Business.Service;


import com.example.atomictype.Business.Entity.Role;
import com.example.atomictype.Business.Entity.User;
import com.example.atomictype.Persistance.Repository.RoleRepository;
import com.example.atomictype.Persistance.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService implements UserServiceInterface, UserDetailsService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to a database", user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Getting user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public User getUserByUsername(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public boolean hasEmail(String email) {
        return userRepo.findByEmail(email) != null;
    }

    @Override
    public boolean hasUser(String username) {
        return userRepo.findByUsername(username) != null;
    }


    @Override
    public void addFriend(String username, String friendName) {
        log.info("Adding new friend {} to a user {}", friendName, username);
        User user = userRepo.findByUsername(username);
        User friend = userRepo.findByUsername(friendName);
        user.getFriends().add(friend);
        friend.getFriends().add(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null){
            log.error("User is not found");
            throw  new UsernameNotFoundException("User is not found");
        } else {
            log.info("User {} is found ", username);
        }
        Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("user"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


}
