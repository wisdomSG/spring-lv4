package com.example.springlv4.dto;

import com.example.springlv4.entity.Like;
import com.example.springlv4.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostResponseDto {
    private Long id;
    private String title;
    private String username;
    private String content;
    private String  createdAt;
    private String modifiedAt;
    private Integer like;
    private List<CommentResponseDto> comment;





    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.username = post.getUser().getUsername();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAtFormatted();
        this.modifiedAt = post.getModifiedAtFormatted();
        this.like = post.getLikeList().size();
        this.comment = post.getCommentList()
                .stream()
                .map(CommentResponseDto::new)
                .toList();


    }

}
