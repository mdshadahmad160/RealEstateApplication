package org.psa.RealEstate.repository;
import org.psa.RealEstate.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AgentRepository extends JpaRepository<Agent, Long> {
}
