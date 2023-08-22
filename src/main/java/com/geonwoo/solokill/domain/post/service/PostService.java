package com.geonwoo.solokill.domain.post.service;

import com.geonwoo.solokill.domain.member.exception.MemberNotFoundException;
import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;
import com.geonwoo.solokill.domain.post.exception.PostNotFoundException;
import com.geonwoo.solokill.domain.post.model.Post;
import com.geonwoo.solokill.domain.post.model.dto.PostResponse;
import com.geonwoo.solokill.domain.post.model.dto.PostSaveRequest;
import com.geonwoo.solokill.domain.post.repository.PostRepository;
import com.geonwoo.solokill.domain.video.service.AwsS3UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final AwsS3UploadService awsS3UploadService;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(PostSaveRequest postSaveRequest, MultipartFile multipartFile, Long memberId) {
        String videoUrl = awsS3UploadService.uploadFiles(multipartFile, multipartFile.getName());
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        Post post = new Post(member, postSaveRequest.title(), postSaveRequest.content(), videoUrl);
        postRepository.save(post);
        return post.getId();
    }

    public PostResponse findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        return PostResponse.toPostResponse(post);
    }

    @Transactional
    public void update(Long id, String content) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post.updateContent(content);
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

}
