package org.psa.RealEstate.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDto {

    private int rating; // Numeric rating (e.g., on a scale from 1 to 5)
    private String title; // Short title for the review
    private String content; // Main text of the review
    private LocalDateTime reviewDate;
    private String status; // e.g., pending, published, hidden
    private int likes;
    private int dislikes;

}
