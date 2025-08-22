package dev.evolting.rolebaseauthorization.repositories;

import dev.evolting.rolebaseauthorization.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {
}
