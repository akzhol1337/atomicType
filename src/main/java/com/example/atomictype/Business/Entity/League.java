package com.example.atomictype.Business.Entity;

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
    //info
    @Id
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
