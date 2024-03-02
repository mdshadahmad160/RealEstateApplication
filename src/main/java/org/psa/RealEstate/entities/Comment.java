package org.psa.RealEstate.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "review_id")
        private Review parentReview;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        private String content;
        @UpdateTimestamp
        private LocalDateTime commentDate;



}
