package org.psa.RealEstate.repository;
import org.psa.RealEstate.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property,Long> {
}
