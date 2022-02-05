package com.example.atomictype.Business.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity @Data
public class AUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(unique = true /*nullable = false*/)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true /*nullable = false*/)
    private String username;

    public AUser() {
    }

    public AUser(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public AUser(Long id, String email, String password, String username) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    @ManyToMany
    private Collection<AUser> friends = new ArrayList<>();
//    private Long totalRaces;
//    private int avgSpeed;
//    private int bestRace;
//    private int lastRace;
//    private Long racesWon;


}

