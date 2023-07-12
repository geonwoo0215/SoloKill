package com.geonwoo.solokill.domain.post.model;

import com.geonwoo.solokill.domain.member.model.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

	private String title;

	private String content;

	@ManyToOne
	private Member member;

	private String videoUrl;

	public Post(String title, String content, Member member, String videoUrl) {
		this.title = title;
		this.content = content;
		this.member = member;
		this.videoUrl = videoUrl;
	}

}
