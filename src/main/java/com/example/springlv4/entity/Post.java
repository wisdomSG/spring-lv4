package com.example.springlv4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="posts")
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //id

    @Column(name="title")
    private String title;   //제목


    @Column(name ="content",nullable = false, length = 500)
    private String content;//작성내용

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.REMOVE})
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = {CascadeType.REMOVE})
    private List<Like> LikeList = new ArrayList<>();

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setContent(String content) {
        this.content = content;
    }
}
