package org.psa.RealEstate.repository;
import org.psa.RealEstate.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
