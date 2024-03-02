package org.psa.RealEstate.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;


    private int rating; // Numeric rating (e.g., on a scale from 1 to 5)
    private String title; // Short title for the review
    private String content; // Main text of the review
    @UpdateTimestamp
    private LocalDateTime reviewDate;
    private String status; // e.g., pending, published, hidden
    private int likes;
    private int dislikes;



       @OneToMany(mappedBy = "parentReview",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<Comment> comments=new HashSet<>();

}



