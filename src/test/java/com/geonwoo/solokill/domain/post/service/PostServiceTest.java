package com.geonwoo.solokill.domain.post.service;

import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;
import com.geonwoo.solokill.domain.post.exception.PostNotFoundException;
import com.geonwoo.solokill.domain.post.model.Post;
import com.geonwoo.solokill.domain.post.model.dto.PostResponse;
import com.geonwoo.solokill.domain.post.model.dto.PostSaveRequest;
import com.geonwoo.solokill.domain.post.repository.PostRepository;
import com.geonwoo.solokill.domain.video.service.AwsS3UploadService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private AwsS3UploadService awsS3UploadService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private PostService postService;

    @DisplayName("[성공] 게시글 저장에 성공한다")
    @Test
    void save_success() throws IOException {

        String videoUrl = "videoUrl";
        String fileName = "testVideo1";
        String contentType = "webm";
        String filePath = "src/test/resources/testVideo/" + fileName + "." + contentType;
        FileInputStream fileInputStream = new FileInputStream(filePath);

        String email = "email";
        String password = "password";
        String nickname = "nickname";
        Long memberId = 1L;
        Member member = new Member(email, password, nickname);

        String title = "title";
        String content = "content";
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                fileName,
                fileName + "." + contentType,
                contentType,
                fileInputStream
        );
        PostSaveRequest postSaveRequest = new PostSaveRequest(title, content);

        when(awsS3UploadService.uploadFiles(mockMultipartFile, fileName)).thenReturn(videoUrl);
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        postService.save(postSaveRequest, mockMultipartFile, memberId);

        verify(awsS3UploadService).uploadFiles(mockMultipartFile, fileName);
        verify(memberRepository).findById(memberId);

    }

    @DisplayName("[성공] 게시글 조회에 성공한다")
    @Test
    void findById_success() {

        String email = "email";
        String password = "password";
        String nickname = "nickname";
        Member member = new Member(email, password, nickname);

        String title = "title";
        String content = "content";
        String videoUrl = "videoUrl";
        Long postId = 1L;
        Post post = new Post(member, title, content, videoUrl);

        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        PostResponse postResponse = postService.findById(postId);

        Assertions.assertThat(postResponse)
                .hasFieldOrPropertyWithValue("title", post.getTitle())
                .hasFieldOrPropertyWithValue("content", post.getContent())
                .hasFieldOrPropertyWithValue("videoUrl", post.getVideoUrl());

        Mockito.verify(postRepository).findById(postId);
    }

    @DisplayName("[성공] 게시글 수정에 성공한다")
    @Test
    void update_success() {

        String email = "email";
        String password = "password";
        String nickname = "nickname";
        Member member = new Member(email, password, nickname);

        String title = "title";
        String content = "content";
        String videoUrl = "videoUrl";
        String updateContent = "updateContent";
        Long postId = 1L;
        Post post = new Post(member, title, content, videoUrl);

        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        postService.update(postId, updateContent);

        Assertions.assertThat(post).hasFieldOrPropertyWithValue("content", updateContent);
        Mockito.verify(postRepository).findById(postId);


    }

    @DisplayName("[실패] id로 게시글을 조회하지 못하면 수정에 실패한다.")
    @Test
    void update_fail() {

        Long postId = 2L;
        String updateContent = "updateContent";
        Mockito.when(postRepository.findById(postId)).thenThrow(PostNotFoundException.class);

        Assertions.assertThatThrownBy(() -> postService.update(postId, updateContent)).isInstanceOf(PostNotFoundException.class);

        verify(postRepository).findById(postId);
    }


}