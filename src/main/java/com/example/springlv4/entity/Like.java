package com.example.springlv4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "postId", nullable = false)
    @ManyToOne
    private Post post;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    @JoinColumn(name = "commentId", nullable = false)
    @ManyToOne
    private Comment comment;

    public Like(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }

    public Like(Post post, User user) {
        this.post = post;
        this.user = user;
    }
}
