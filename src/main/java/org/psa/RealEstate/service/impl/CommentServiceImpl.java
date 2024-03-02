package org.psa.RealEstate.service.impl;
import org.psa.RealEstate.entities.Comment;
import org.psa.RealEstate.entities.Review;
import org.psa.RealEstate.entities.User;
import org.psa.RealEstate.exception.CommentNotFoundException;
import org.psa.RealEstate.exception.ReviewNotFoundException;
import org.psa.RealEstate.exception.UserNotFoundException;
import org.psa.RealEstate.payload.CommentDto;
import org.psa.RealEstate.repository.CommentRepository;
import org.psa.RealEstate.repository.ReviewRepository;
import org.psa.RealEstate.repository.UserRepository;
import org.psa.RealEstate.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper mapper;

    public Comment mapToEntity(CommentDto commentDto){
       /* Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setParentReview();
        comment.setUser();
        comment.setContent(commentDto.getContent());
        comment.setCommentDate(commentDto.getCommentDate());
        return comment;*/
        Comment comment=mapper.map(commentDto,Comment.class);
        return comment;
    }
    public CommentDto mapToDto(Comment comment){
       /* CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setCommentDate(comment.getCommentDate());
        return commentDto;*/
        CommentDto dto=mapper.map(comment,CommentDto.class);
        return dto;
    }

    @Override
    public CommentDto saveComment(long userId, long reviewId, CommentDto commentDto) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Review review=reviewRepository.findById(reviewId).orElseThrow(
                ()-> new ReviewNotFoundException("Invalid Review Id: ")
        );
        Comment comment=mapToEntity(commentDto);
        comment.setUser(user);
        comment.setParentReview(review);
        Comment newComment=commentRepository.save(comment);
        CommentDto dto=mapToDto(newComment);
        return dto;
    }

    @Override
    public CommentDto updateComment(long userId, long reviewId, long commentId, CommentDto commentDto) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Review review=reviewRepository.findById(reviewId).orElseThrow(
                ()-> new ReviewNotFoundException("Invalid Review Id Plz Enter Valid Review Id: ")
        );
        Comment comment=commentRepository.findById(commentId).orElseThrow(
                ()-> new CommentNotFoundException("Invalid Comment Id Plz Enter Valid Id: ")
        );
        comment.setContent(commentDto.getContent());
        comment.setCommentDate(commentDto.getCommentDate());
        Comment newComment=commentRepository.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public CommentDto findCommentById(long userId, long reviewId, long commentId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Review review=reviewRepository.findById(reviewId).orElseThrow(
                ()-> new ReviewNotFoundException("Invalid Review Id: ")
        );
        Comment comment=commentRepository.findById(commentId).orElseThrow(
                ()-> new CommentNotFoundException("Invalid Comment Id: ")
        );
        CommentDto dto=mapToDto(comment);
        return dto;
    }

    @Override
    public List<CommentDto> getAllComment() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> commentDto=comments.stream().map(comment -> mapToDto(comment))
                .collect(Collectors.toList());
        return commentDto;
    }

    @Override
    public void deleteComment(long userId, long reviewId, long commentId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Review review=reviewRepository.findById(reviewId).orElseThrow(
                ()-> new ReviewNotFoundException("Invalid Review Id: ")
        );
        Comment comment=commentRepository.findById(commentId).orElseThrow(
                ()-> new CommentNotFoundException("Invalid Comment Id: ")
        );
        commentRepository.delete(comment);

    }
}
