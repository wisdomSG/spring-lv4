package com.example.springlv4.controller;

import com.example.springlv4.dto.PostAllResponseDto;
import com.example.springlv4.dto.PostRequestDto;
import com.example.springlv4.dto.PostResponseDto;
import com.example.springlv4.security.UserDetailsImpl;
import com.example.springlv4.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "PostController",description="게시물 API")
public class PostController {


    private final PostService postService;


    // 게시물 조회
    @GetMapping("/post")
    @Operation(summary = "게시물 전체 조회 API")
    public List<PostAllResponseDto> getAllPost() {
        return postService.getAllPost();
    }

    // 게시물 단건 조회
    @GetMapping("post/{id}")
    @Operation(summary = "게시물 단건 조회 API")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }


    // 게시물 생성
    @PostMapping("/post")
    @Operation(summary = "게시물 생성 API")
    public ResponseEntity<String> createPost(@RequestBody PostRequestDto dto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.createPost(dto, userDetails.getUser());
        return ResponseEntity.ok().body("게시물 생성 성공");
    }

    // 게시물 수정
    @PutMapping("/post/{id}")
    @Operation(summary = "게시물 수정 API")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto dto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(userDetails.getUser(), id, dto);
    }

    // 게시물 삭제
    @DeleteMapping("/post/{id}")
    @Operation(summary = "게시물 삭제 API")
    public ResponseEntity<String> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.DeletePost(userDetails.getUser(), id);
        return ResponseEntity.ok().body("게시물 삭제 성공");
    }

}
