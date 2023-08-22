package com.geonwoo.solokill.domain.comment.model;

import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.post.model.Post;
import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Post post;

    private String content;

}
