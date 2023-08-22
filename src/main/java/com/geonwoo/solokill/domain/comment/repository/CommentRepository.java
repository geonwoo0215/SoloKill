package com.geonwoo.solokill.domain.comment.repository;

import com.geonwoo.solokill.domain.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
