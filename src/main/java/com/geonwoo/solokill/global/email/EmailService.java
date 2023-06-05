package com.geonwoo.solokill.global.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.geonwoo.solokill.global.email.dto.EmailDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender mailSender;

	public void sendEmail(EmailDTO emailDTO) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("${email.address}");
		message.setTo(emailDTO.email());
		message.setSubject(emailDTO.title());
		message.setText(emailDTO.content());
		mailSender.send(message);

	}
}
