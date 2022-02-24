package com.example.atomictype.Business.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class UserResult {
    @Id
    private Long id;
    private Long userId;
    private String username;
    private Integer speed;
    private Float accuracy;
    private Integer place;
}
