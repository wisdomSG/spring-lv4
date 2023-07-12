package com.example.springlv4.service;

import com.example.springlv4.dto.CommentRequestDto;
import com.example.springlv4.entity.Comment;
import com.example.springlv4.entity.Post;
import com.example.springlv4.entity.User;
import com.example.springlv4.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostService postService;



    public void createComment(CommentRequestDto dto, User user) {
        Post post = postService.findPost(dto.getPostId());
        Comment comment = new Comment(dto.getBody(), post, user);

        commentRepository.save(comment);

    }


    @Transactional
    public void updateComment(Long commentId, CommentRequestDto dto, User user) {
        Comment comment = findComment(commentId);

        if (comment.getUser().equals(user)) {
            comment.setBody(dto.getBody());
        } else {
            throw new IllegalArgumentException("직접 장성한 댓글이 아닙니다.");
        }
    }

    public void deleteComment(Long commentId, User user) {
        Comment comment = findComment(commentId);
        if (!comment.getUser().equals(user)) {
            throw new IllegalArgumentException("직접 작성한 댓글이 아닙니다.");
        }

        commentRepository.delete(comment);
    }

    private Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글은 존재하지 않습니다."));
    }
}
