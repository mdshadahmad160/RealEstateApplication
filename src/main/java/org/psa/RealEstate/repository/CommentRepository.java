package org.psa.RealEstate.repository;
import org.psa.RealEstate.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
