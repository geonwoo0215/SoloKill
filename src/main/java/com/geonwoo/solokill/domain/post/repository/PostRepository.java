package com.geonwoo.solokill.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geonwoo.solokill.domain.post.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
