package com.example.springlv4.controller;

import com.example.springlv4.dto.CommentRequestDto;
import com.example.springlv4.entity.User;
import com.example.springlv4.security.UserDetailsImpl;
import com.example.springlv4.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 저장
    @PostMapping("/post/comment")
    public ResponseEntity<String> createComment(@RequestBody CommentRequestDto dto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.createComment(dto, userDetails.getUser());
        return ResponseEntity.ok().body("postId : " + dto.getPostId() + ", 댓글 작성완료");
    }

    // 댓글 수정
    @PutMapping("/post/comment/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto dto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.updateComment(commentId, dto, userDetails.getUser());
        return ResponseEntity.ok().body("댓글 수정 완료");
    }

    // 댓글 삭제
    @DeleteMapping("/post/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId, userDetails.getUser());
        return ResponseEntity.ok().body("댓글 삭제 완료");
    }
}

