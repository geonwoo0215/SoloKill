package com.geonwoo.solokill.domain.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;
import com.geonwoo.solokill.domain.post.converter.PostConverter;
import com.geonwoo.solokill.domain.post.dto.PostResponse;
import com.geonwoo.solokill.domain.post.dto.PostSaveRequest;
import com.geonwoo.solokill.domain.post.model.Post;
import com.geonwoo.solokill.domain.post.repository.PostRepository;
import com.geonwoo.solokill.domain.video.service.AwsS3UploadService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

	private final AwsS3UploadService awsS3UploadService;
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public Long save(PostSaveRequest postSaveRequest, MultipartFile multipartFile, Long memberId) {
		String uploadUrl = awsS3UploadService.uploadFiles(multipartFile, "video");
		Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
		Post post = PostConverter.toPost(postSaveRequest, uploadUrl, member);
		postRepository.save(post);
		return post.getId();
	}

	public PostResponse findById(Long id) {
		Post post = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		return PostConverter.toPostResponse(post);
	}

	@Transactional
	public void delete(Long id) {
		postRepository.deleteById(id);
	}

}
