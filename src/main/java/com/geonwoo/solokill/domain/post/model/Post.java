package com.geonwoo.solokill.domain.post.model;

import com.geonwoo.solokill.domain.member.model.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Member member;

    private String title;

    private String content;

    private String videoUrl;

    public Post(Member member, String title, String content, String videoUrl) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.videoUrl = videoUrl;
    }

    public void updateContent(String content) {
        this.content = content;
    }

}
