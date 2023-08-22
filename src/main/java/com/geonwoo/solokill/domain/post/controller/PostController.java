package com.geonwoo.solokill.domain.post.controller;

import com.geonwoo.solokill.domain.member.model.dto.response.MemberResponse;
import com.geonwoo.solokill.domain.post.model.dto.PostResponse;
import com.geonwoo.solokill.domain.post.model.dto.PostSaveRequest;
import com.geonwoo.solokill.domain.post.service.PostService;
import com.geonwoo.solokill.global.auth.annotation.LoginMember;
import com.geonwoo.solokill.global.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Long>> save(HttpServletRequest request,
                                                  @RequestPart("video") MultipartFile multipartFile,
                                                  @RequestPart("post") PostSaveRequest postSaveRequest,
                                                  @LoginMember MemberResponse memberDTO) {

        Long savedId = postService.save(postSaveRequest, multipartFile, memberDTO.id());
        return ResponseEntity.created(URI.create(request.getRequestURI() + "/" + savedId))
                .body(new ApiResponse<>(savedId));
    }

    @GetMapping(value = "/post/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PostResponse>> findById(@PathVariable("postId") Long postId) {
        PostResponse postResponse = postService.findById(postId);

        return ResponseEntity.ok(new ApiResponse<>(postResponse));
    }

    @DeleteMapping(value = "/post/{postId}")
    public ResponseEntity<Void> delete(@PathVariable("postId") Long postId) {
        postService.delete(postId);

        return ResponseEntity.noContent().build();
    }

}
