package org.psa.RealEstate.service.impl;
import org.psa.RealEstate.entities.Property;
import org.psa.RealEstate.entities.Review;
import org.psa.RealEstate.entities.User;
import org.psa.RealEstate.exception.PropertyNotFoundException;
import org.psa.RealEstate.exception.ReviewNotFoundException;
import org.psa.RealEstate.exception.UserNotFoundException;
import org.psa.RealEstate.payload.ReviewDto;
import org.psa.RealEstate.repository.PropertyRepository;
import org.psa.RealEstate.repository.ReviewRepository;
import org.psa.RealEstate.repository.UserRepository;
import org.psa.RealEstate.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ModelMapper mapper;

    public Review mapToEntity(ReviewDto reviewDto){
      /*  Review review = new Review();
        review.setId();
        review.setUser();
        review.setProperty();
        review.setRating(reviewDto.getRating());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setReviewDate(reviewDto.getReviewDate());
        review.setStatus(reviewDto.getStatus());
        review.setLikes(reviewDto.getLikes());
        review.setDislikes(reviewDto.getDislikes());
        review.setComments();
        return review;*/
        Review review=mapper.map(reviewDto,Review.class);
        return review;
    }
    public ReviewDto mapToDto(Review review){
        /*ReviewDto reviewDto = new ReviewDto();
        reviewDto.setRating(review.getRating());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setReviewDate(review.getReviewDate());
        reviewDto.setStatus(review.getStatus());
        reviewDto.setLikes(review.getLikes());
        reviewDto.setDislikes(review.getDislikes());
        return reviewDto;*/
        ReviewDto dto=mapper.map(review,ReviewDto.class);
        return dto;
    }
    @Override
    public ReviewDto saveReview(long userId, long propertyId, ReviewDto reviewDto) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Invalid Property Id: ")
        );
        Review review=mapToEntity(reviewDto);
        review.setUser(user);
        review.setProperty(property);
        Review newReview=reviewRepository.save(review);
        ReviewDto dto=mapToDto(newReview);
        return dto;
    }

    @Override
    public ReviewDto updateReview(long userId, long propertyId, long reviewId, ReviewDto reviewDto) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Invalid Property Id: ")
        );
        Review review=reviewRepository.findById(reviewId).orElseThrow(
                ()-> new ReviewNotFoundException("Invalid Review Id: ")
        );
        review.setRating(reviewDto.getRating());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setReviewDate(reviewDto.getReviewDate());
        review.setStatus(reviewDto.getStatus());
        review.setLikes(reviewDto.getLikes());
        review.setDislikes(reviewDto.getDislikes());
        Review newReview=reviewRepository.save(review);
       /* ReviewDto dto=mapToDto(newReview);
        return dto;*/
        return mapToDto(newReview);
    }

    @Override
    public ReviewDto findReviewById(long userId, long propertyId, long reviewId) {
        User  user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Invalid Property Id: ")
        );
        Review review=reviewRepository.findById(reviewId).orElseThrow(
                ()-> new ReviewNotFoundException("Review With This Id DoesNot Exists: ")
        );
        ReviewDto dto=mapToDto(review);
        return dto;
    }

    @Override
    public List<ReviewDto> getAllReview() {
        List<Review> reviews=reviewRepository.findAll();
        List<ReviewDto> dto=reviews.stream().map(review -> mapToDto(review))
                .collect(Collectors.toList());
        return dto;
    }

    @Override
    public void deleteReview(long userId, long propertyId, long reviewId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Property Id Not Valid: ")
        );
        Review review=reviewRepository.findById(reviewId).orElseThrow(
                ()-> new ReviewNotFoundException("Review Not Valid PLz Enter Valid Review Id: ")
        );
        reviewRepository.delete(review);

    }
}
