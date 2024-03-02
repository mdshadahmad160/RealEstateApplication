package org.psa.RealEstate.repository;
import org.psa.RealEstate.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookingRepository extends JpaRepository<Booking,Long> {
}
