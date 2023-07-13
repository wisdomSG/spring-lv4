package com.example.springlv4.dto;

import com.example.springlv4.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class CommentResponseDto {
    private Long id;
    private String body;
    private String  createdAt;
    private String modifiedAt;
    private String username;
    private Integer like;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.body = comment.getBody();
        this.username = comment.getUser().getUsername();
        this.createdAt = comment.getCreatedAtFormatted();
        this.modifiedAt = comment.getModifiedAtFormatted();
        this.like = comment.getLikeList().size();
    }
}
