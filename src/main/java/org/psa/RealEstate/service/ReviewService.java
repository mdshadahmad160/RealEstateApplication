package org.psa.RealEstate.service;
import org.psa.RealEstate.payload.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto saveReview(long userId, long propertyId, ReviewDto reviewDto);
    ReviewDto updateReview(long userId,long propertyId,long reviewId,ReviewDto reviewDto);
    ReviewDto findReviewById(long userId,long propertyId,long reviewId);
    List<ReviewDto> getAllReview();
    void deleteReview(long userId,long propertyId,long reviewId);
}
