package com.geonwoo.solokill.domain.like.model;

import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.post.model.Post;
import jakarta.persistence.*;

@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member user;

    @ManyToOne
    private Post post;


}
