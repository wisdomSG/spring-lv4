package com.example.springlv4.controller;

import com.example.springlv4.dto.CommentRequestDto;
import com.example.springlv4.entity.User;
import com.example.springlv4.security.UserDetailsImpl;
import com.example.springlv4.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name="CommentController",description="댓글 API")
public class CommentController {

    private final CommentService commentService;

    // 댓글 저장
    @PostMapping("/post/comment")
    @Operation(summary = "댓글 저장 API")
    public ResponseEntity<String> createComment(@RequestBody CommentRequestDto dto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.createComment(dto, userDetails.getUser());
        return ResponseEntity.ok().body("postId : " + dto.getPostId() + ", 댓글 작성완료");
    }

    // 댓글 수정
    @PutMapping("/post/comment/{commentId}")
    @Operation(summary = "댓글 수정 API")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto dto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.updateComment(commentId, dto, userDetails.getUser());
        return ResponseEntity.ok().body("댓글 수정 완료");
    }

    // 댓글 삭제
    @DeleteMapping("/post/comment/{commentId}")
    @Operation(summary = "댓글 삭제 API")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId, userDetails.getUser());
        return ResponseEntity.ok().body("댓글 삭제 완료");
    }
}

