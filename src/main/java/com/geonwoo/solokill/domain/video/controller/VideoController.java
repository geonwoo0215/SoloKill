package com.geonwoo.solokill.domain.video.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.geonwoo.solokill.domain.video.service.AwsS3UploadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class VideoController {

	private final AwsS3UploadService awsS3UploadService;

	@PostMapping("/api/v1/upload")
	public ResponseEntity<Void> uploadImage(@RequestPart MultipartFile file) {
		awsS3UploadService.uploadFiles(file, "video");
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
