package com.geonwoo.solokill.domain.post.model.dto;

import com.geonwoo.solokill.domain.post.model.Post;

public record PostResponse(
        Long id,
        String title,
        String content,
        String videoUrl
) {

    public static PostResponse toPostResponse(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getVideoUrl());
    }
}
