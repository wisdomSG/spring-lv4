package com.example.springlv4.service;

import com.example.springlv4.entity.Comment;
import com.example.springlv4.entity.Like;
import com.example.springlv4.entity.Post;
import com.example.springlv4.entity.User;
import com.example.springlv4.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;
    private final CommentService commentService;
    public void createPostLike(Long postId, User user) {

        Post post = postService.findPost(postId);

        Like like = new Like(post, user);

        // 사용자가 해당 게시물에 좋아요를 누른 적이 없는 경우
        if(likeRepository.findByUserAndPost(user, post).isEmpty()) { // 누른적이 없는 경우: true 반환
            // 좋아요 정보를 저장
            likeRepository.save(like);
        } else {
            throw new IllegalArgumentException("좋아요를 누른 적이 있습니다.");
        }

    }

    public void deletePostLike(Long likeId, User user) {


        Like like = findLike(likeId);

        if(!like.getUser().equals(user)) {
            throw new RejectedExecutionException("좋아요를 클릭한 유저가 아닙니다.");

        }

        likeRepository.delete(like);

    }
    public void createCommentLike(Long commentId, User user) {
        Comment comment = commentService.findComment(commentId);
        Like like = new Like(user, comment); // path로 postId 안받고 입력할려면 어떻게 해야할지

        // 사용자가 해당 댓글에 좋아요를 누른 적이 없는 경우
        if(likeRepository.findByUserAndComment(user, comment).isEmpty()) { // 누른적이 없는 경우: true 반환
            // 좋아요 정보를 저장
            likeRepository.save(like);
        } else {
            throw new IllegalArgumentException("좋아요를 누른 적이 있습니다.");
        }
    }

    public void deleteCommentLike(Long likeId, User user) {

        Like like = findLike(likeId);

        if(!like.getUser().equals(user)) {
            throw new RejectedExecutionException("좋아요를 클릭한 유저가 아닙니다.");

        }
        likeRepository.delete(like);
    }

    public Like findLike(Long id) {
        return likeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("좋아요를 클릭한 적이 없습니다."));

    }
}
