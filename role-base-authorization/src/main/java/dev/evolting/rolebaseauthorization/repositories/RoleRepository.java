package dev.evolting.rolebaseauthorization.repositories;

import dev.evolting.rolebaseauthorization.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
