package com.geonwoo.solokill.domain.post.converter;

import org.springframework.stereotype.Component;

import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.post.dto.PostResponse;
import com.geonwoo.solokill.domain.post.dto.PostSaveRequest;
import com.geonwoo.solokill.domain.post.model.Post;

@Component
public class PostConverter {

	public static Post toPost(PostSaveRequest postSaveRequest, String videoUrl, Member member) {
		return new Post(postSaveRequest.title(), postSaveRequest.content(), member, videoUrl);
	}

	public static PostResponse toPostResponse(Post post) {
		return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getVideoUrl(),
			post.getMember().getNickname());
	}
}
