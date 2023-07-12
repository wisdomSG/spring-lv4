package com.example.springlv4.dto;

import com.example.springlv4.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostAllResponseDto {
    private Long id;
    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public PostAllResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.username = post.getUser().getUsername();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();

    }
}
