package com.example.springlv4.controller;


import com.example.springlv4.entity.Like;
import com.example.springlv4.entity.User;
import com.example.springlv4.security.UserDetailsImpl;
import com.example.springlv4.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // 게시물 좋아요
    @PostMapping("/post/{postId}/like")
    public ResponseEntity<String> createPostLike(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.createPostLike(postId, userDetails.getUser());
        return ResponseEntity.ok().body("게시물 좋아요 완료 ~");
    }

    // 게시물 좋아요취소
    @DeleteMapping("/post/like/{likeId}")
    public ResponseEntity<String> deletePostLike(@PathVariable Long likeId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.deletePostLike(likeId, userDetails.getUser());
        return ResponseEntity.ok().body("게시물 좋아요 취소 ");
    }

    // 댓글 좋아요
    @PostMapping("/comment/{commentId}/like")
    public ResponseEntity<String> createCommentLike(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.createCommentLike(commentId, userDetails.getUser());
        return ResponseEntity.ok().body("댓글 좋아요 완료 !");
    }

    // 댓글 좋아요 취소
    @DeleteMapping("/comment/like/{likeId}")
    public ResponseEntity<String> deleteCommentLike(@PathVariable Long likeId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.deleteCommentLike(likeId, userDetails.getUser());
        return ResponseEntity.ok().body("댓글 좋아요 취소");
    }


}
