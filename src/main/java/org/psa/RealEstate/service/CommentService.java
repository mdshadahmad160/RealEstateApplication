package org.psa.RealEstate.service;
import org.psa.RealEstate.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto saveComment(long userId, long reviewId, CommentDto commentDto);
    CommentDto updateComment(long userId,long reviewId,long commentId,CommentDto commentDto);
    CommentDto findCommentById(long userId,long reviewId,long commentId);
    List<CommentDto> getAllComment();
    void deleteComment(long userId,long reviewId,long commentId);
}
