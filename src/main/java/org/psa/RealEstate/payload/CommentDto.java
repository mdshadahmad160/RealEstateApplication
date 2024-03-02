package org.psa.RealEstate.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private long id;
    private String content;
    private LocalDateTime commentDate;

}
