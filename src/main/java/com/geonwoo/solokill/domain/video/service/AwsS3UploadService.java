package com.geonwoo.solokill.domain.video.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.geonwoo.solokill.global.properties.NCPProperties;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AwsS3UploadService {

	private final NCPProperties ncpProperties;
	private final AmazonS3 s3;

	public String uploadFiles(MultipartFile multipartFile, String dirName) {
		File uploadFile = convertMultipartFileToFile(multipartFile)
			.orElseThrow(() -> new IllegalArgumentException("파일 변환을 실패하였습니다."));
		return upload(uploadFile, dirName);
	}

	private String upload(File uploadFile, String filePath) {
		String fileName = filePath + "/" + UUID.randomUUID() + uploadFile.getName();
		String uploadImageUrl = putS3(uploadFile, fileName);
		removeNewFile(uploadFile);
		return uploadImageUrl;
	}

	private String putS3(File uploadFile, String fileName) {
		s3.putObject(new PutObjectRequest(ncpProperties.bucketName(), fileName, uploadFile).withCannedAcl(
			CannedAccessControlList.PublicRead));
		return s3.getUrl(ncpProperties.bucketName(), fileName).toString();
	}

	private void removeNewFile(File targetFile) {
		targetFile.delete();
	}

	private Optional<File> convertMultipartFileToFile(MultipartFile multipartFile) {
		File file = new File(System.getProperty("user.dir") + "/" + multipartFile.getOriginalFilename());

		try {
			if (file.createNewFile()) {
				try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
					fileOutputStream.write(multipartFile.getBytes());
				}
				return Optional.of(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

}
