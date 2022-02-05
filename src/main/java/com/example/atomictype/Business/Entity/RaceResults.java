package com.example.atomictype.Business.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RaceResults {
    @Id
    private Long id;
    @OneToMany
    private List<UserResult> users;
    private Date date;
}
