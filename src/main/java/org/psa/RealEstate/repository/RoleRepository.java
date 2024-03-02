package org.psa.RealEstate.repository;
import org.psa.RealEstate.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {

    //Optional<Role> findByName(String name);
}
