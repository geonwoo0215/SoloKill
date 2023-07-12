package com.geonwoo.solokill.domain.post.dto;

public record PostResponse(
	Long id,
	String title,
	String content,
	String videoUrl,
	String nickname
) {
}
