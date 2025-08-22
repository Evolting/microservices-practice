package dev.evolting.rolebaseauthorization.repositories;

import dev.evolting.rolebaseauthorization.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * from users u where u.username = :username", nativeQuery = true)
    User findByUsername(@Param("username") String username);
}
