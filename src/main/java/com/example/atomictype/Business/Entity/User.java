package com.example.atomictype.Business.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
public class User {
    // info
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(unique = true /*,nullable = false*/)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true /*,nullable = false*/)
    private String username;

    private String profile_photo;
    private String account_type;
    private String car_photo;
    private String country;
    private Date registration_date;

    // statistics
    private String speed_rank;
    private String experience_rank;
    private Integer average_wpm_last10;
    private Integer best_wpm;
    private Integer average_wpm_full;
    private Integer number_of_races;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @ManyToMany
    private Collection<User> friends = new ArrayList<>();

    @ManyToMany
    private List<League> leagues;

    @ManyToMany
    private List<RaceResults> racesHistory;

    public User() {
    }

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User(Long id, String email, String password, String username) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
    }

}
