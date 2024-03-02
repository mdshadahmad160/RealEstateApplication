package org.psa.RealEstate.repository;
import org.psa.RealEstate.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
