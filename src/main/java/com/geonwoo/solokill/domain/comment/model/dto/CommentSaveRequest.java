package com.geonwoo.solokill.domain.comment.model.dto;

public record CommentSaveRequest(
        Long postId,

        String content
) {
    
}
