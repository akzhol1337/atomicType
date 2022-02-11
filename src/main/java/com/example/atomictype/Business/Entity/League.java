package com.example.atomictype.Business.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class League {
    public League(String name, String password, String description, Integer slots, Integer min_average_wpm, Boolean full_or_last10, String country) {
        this.name = name;
        this.password = password;
        this.description = description;
        this.slots = slots;
        this.min_average_wpm = min_average_wpm;
        this.full_or_last10 = full_or_last10;
        this.country = country;
    }

    //info
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String description;
    private String profile_photo;
    private String banner_photo;
    // restrictions
    private Integer slots;
    private Integer min_average_wpm;
    private Boolean full_or_last10;
    private String country;
    private Boolean privateLeague;

    @ManyToMany
    private List<User> users;
    @ManyToMany
    private List<User> admins;

}
