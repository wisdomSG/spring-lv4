package com.example.springlv4.controller;

import com.example.springlv4.dto.PostAllResponseDto;
import com.example.springlv4.dto.PostRequestDto;
import com.example.springlv4.dto.PostResponseDto;
import com.example.springlv4.security.UserDetailsImpl;
import com.example.springlv4.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {


    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시물 조회
    @GetMapping("/post")
    public List<PostAllResponseDto> getAllPost() {
        return postService.getAllPost();
    }

    // 게시물 단건 조회
    @GetMapping("post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }


    // 게시물 생성
    @PostMapping("/post")
    public ResponseEntity<String> createPost(@RequestBody PostRequestDto dto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.createPost(dto, userDetails.getUser());
        return ResponseEntity.ok().body("게시물 생성 성공");
    }

    // 게시물 수정
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto dto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(userDetails.getUser(), id, dto);
    }

    // 게시물 삭제
    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.DeletePost(userDetails.getUser(), id);
        return ResponseEntity.ok().body("게시물 삭제 성공");
    }

}
