package org.psa.RealEstate.repository;
import org.psa.RealEstate.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
   /* Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);*/
   Optional<User> findByusername(String username);
}
