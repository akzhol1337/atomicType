package com.example.atomictype.Persistance.Repository;

import com.example.atomictype.Business.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
    //AUser findByEmailOrUsername(String email, String username);
}
