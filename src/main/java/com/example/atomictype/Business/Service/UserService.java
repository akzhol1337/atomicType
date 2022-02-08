package com.example.atomictype.Business.Service;


import com.example.atomictype.Business.Entity.AUser;
import com.example.atomictype.Persistance.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AUser saveUser(AUser AUser) {

        log.info("Saving new user {} to a database", AUser.getEmail());
        AUser.setPassword(passwordEncoder.encode(AUser.getPassword()));
        return userRepository.save(AUser);
    }

    @Override
    public AUser getUser(String username) {
        log.info("Getting user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public AUser getUserByUsername(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean hasEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public boolean hasUser(String username) {
        return userRepository.findByUsername(username) != null;
    }


    @Override
    public void addFriend(String username, String friendName) {
        log.info("Adding new friend {} to a user {}", friendName, username);
        AUser user = userRepository.findByUsername(username);
        AUser friend = userRepository.findByUsername(friendName);
        user.getFriends().add(friend);
        friend.getFriends().add(user);
    }

    @Override
    public List<AUser> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AUser user = userRepository.findByUsername(username);
        if (user == null){
            log.error("User is not found");
            throw  new UsernameNotFoundException("User is not found");
        } else {
            log.info("User {} is found ", username);
        }
        Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("user"));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }


}
