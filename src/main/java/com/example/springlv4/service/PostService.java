package com.example.springlv4.service;

import com.example.springlv4.dto.PostAllResponseDto;
import com.example.springlv4.dto.PostRequestDto;
import com.example.springlv4.dto.PostResponseDto;
import com.example.springlv4.entity.Post;
import com.example.springlv4.entity.User;
import com.example.springlv4.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor

public class PostService {

    private final PostRepository postRepository;


    // 게시물 생성
    public void createPost(PostRequestDto dto, User user) {
        String title = dto.getTitle();
        String contents = dto.getContent();

        Post post = new Post(title, contents, user);
        postRepository.save(post);
    }

    // 게시물 전체 조회
    public List<PostAllResponseDto> getAllPost() {
        List<PostAllResponseDto> responseDtoList = postRepository.findAll()
                .stream()
                .map(PostAllResponseDto::new)
                .toList();

        return responseDtoList;
    }

    // 게시물 단건 조회
    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);

        return new PostResponseDto(post);
    }

    // 게시물 수정
    @Transactional
    public PostResponseDto updatePost(User user, Long id, PostRequestDto requestDto) {
        Post post = findPost(id);

        if (!post.getUser().equals(user)) {
            throw new RejectedExecutionException();
        }
        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());

        return new PostResponseDto(post);

    }

    // 게시물 삭제
    public void DeletePost(User user, Long id) {
        Post post = findPost(id);

        if (!post.getUser().equals(user)) {
            throw new RejectedExecutionException();
        }

        postRepository.delete(post);
    }




    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
    }
}
