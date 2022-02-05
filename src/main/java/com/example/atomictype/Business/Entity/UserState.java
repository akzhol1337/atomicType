package com.example.atomictype.Business.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserState {
    private String method;
    private Long userId;
    private Long raceId;
    private String name;
    private Integer speed;
    private Integer progress;
}
