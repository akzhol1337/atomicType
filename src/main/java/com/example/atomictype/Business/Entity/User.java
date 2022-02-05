package com.example.atomictype.Business.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    // info
    @Id
    private Long id;
    private String username;
    private String email;
    private String password;
    private String profile_photo;
    private String account_type;
    private String car_photo;
    private String country;
    private Date registration_date;
    @ManyToMany
    private List<User> friends;
    @ManyToMany
    private List<League> leagues;
    @ManyToMany
    private List<RaceResults> racesHistory;

    // statistics
    private String speed_rank;
    private String experience_rank;
    private Integer average_wpm_last10;
    private Integer best_wpm;
    private Integer average_wpm_full;
    private Integer number_of_races;
}
