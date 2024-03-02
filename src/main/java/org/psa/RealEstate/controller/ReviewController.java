package org.psa.RealEstate.controller;
import org.psa.RealEstate.payload.ApiResponse;
import org.psa.RealEstate.payload.ReviewDto;
import org.psa.RealEstate.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping("/users/{userId}/properties/{propertyId}/reviews")
    public ResponseEntity<ReviewDto> saveReview(@PathVariable(value = "userId") long userId,
                                                @PathVariable(value = "propertyId") long propertyId,
                                                @RequestBody ReviewDto reviewDto){
        ReviewDto dto=reviewService.saveReview(userId, propertyId, reviewDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PutMapping("/users/{userId}/properties/{propertyId}/reviews/{reviewId}/reviews")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "userId") long userId,
                                                  @PathVariable(value = "propertyId") long propertyId,
                                                  @PathVariable(value = "reviewId") long reviewId,
                                                  @RequestBody ReviewDto reviewDto){
        ReviewDto dto=reviewService.updateReview(userId, propertyId, reviewId, reviewDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/users/{userId}/properties/{propertyId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> findReviewById(@PathVariable(value = "userId") long userId,
                                                    @PathVariable(value = "propertyId") long propertyId,
                                                    @PathVariable(value = "reviewId") long reviewId){
        ReviewDto dto=reviewService.findReviewById(userId, propertyId, reviewId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDto>> getAllUser(){
        List<ReviewDto> dto=reviewService.getAllReview();
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @DeleteMapping("/users/{userId}/properties/{propertyId}/reviews/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable(value = "userId") long userId,
                                                    @PathVariable(value = "propertyId") long propertyId,
                                                    @PathVariable(value = "reviewId") long reviewId){
        reviewService.deleteReview(userId, propertyId, reviewId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Review Deleted Successfully!!!",true),HttpStatus.OK);
    }
}
