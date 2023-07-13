package com.example.springlv4.dto;

import com.example.springlv4.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostAllResponseDto {
    private Long id;
    private String title;
    private String username;
    private String content;
    private String createdAt;
    private String modifiedAt;


    public PostAllResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.username = post.getUser().getUsername();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAtFormatted();
        this.modifiedAt = post.getModifiedAtFormatted();

    }
}
