package com.example.atomictype.Persistance.Repository;

import com.example.atomictype.Business.Entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
