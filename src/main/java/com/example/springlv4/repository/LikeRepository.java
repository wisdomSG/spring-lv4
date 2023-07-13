package com.example.springlv4.repository;

import com.example.springlv4.entity.Like;
import com.example.springlv4.entity.Post;
import com.example.springlv4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndPost(User user, Post post);
}
