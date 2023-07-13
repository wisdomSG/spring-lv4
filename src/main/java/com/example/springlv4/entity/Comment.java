package com.example.springlv4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body")
    private String body;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "comment", cascade = {CascadeType.REMOVE})
    private List<Like> LikeList = new ArrayList<>();

    public Comment(String body, Post post, User user) {
        this.body = body;
        this.post = post;
        this.user = user;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
