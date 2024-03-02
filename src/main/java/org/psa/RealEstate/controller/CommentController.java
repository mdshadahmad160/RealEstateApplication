package org.psa.RealEstate.controller;
import org.psa.RealEstate.payload.ApiResponse;
import org.psa.RealEstate.payload.CommentDto;
import org.psa.RealEstate.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/users/{userId}/reviews/{reviewId}/comments")
    public ResponseEntity<CommentDto> saveComment(@PathVariable(value = "userId") long userId,
                                                  @PathVariable(value = "reviewId") long reviewId,
                                                  @RequestBody CommentDto commentDto){
        CommentDto dto=commentService.saveComment(userId, reviewId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PutMapping("/users/{userId}/reviews/{reviewId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "userId") long userId,
                                                    @PathVariable(value = "reviewId") long reviewId,
                                                    @PathVariable(value = "commentId") long commentId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto dto=commentService.updateComment(userId, reviewId, commentId, commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/users/{userId}/reviews/{reviewId}/comments/{commentId}")
    public ResponseEntity<CommentDto> findCommentById(@PathVariable(value = "userId") long userId,
                                                      @PathVariable(value = "reviewId") long reviewId,
                                                      @PathVariable(value = "commentId") long commentId){
        CommentDto dto=commentService.findCommentById(userId, reviewId, commentId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> getAllComment(){
        List<CommentDto> commentDto=commentService.getAllComment();
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }
    @DeleteMapping("/users/{userId}/reviews/{reviewId}/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable(value = "userId") long userId,
                                                     @PathVariable(value = "reviewId") long reviewId,
                                                     @PathVariable(value = "commentId") long commentId){
        commentService.deleteComment(userId, reviewId, commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully!!",true),HttpStatus.OK);
    }
}
