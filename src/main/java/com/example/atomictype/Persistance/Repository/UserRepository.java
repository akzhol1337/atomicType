package com.example.atomictype.Persistance.Repository;

import com.example.atomictype.Business.Entity.AUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AUser, Long> {
    AUser findByEmail(String email);
    AUser findByUsername(String username);
    //AUser findByEmailOrUsername(String email, String username);
}
