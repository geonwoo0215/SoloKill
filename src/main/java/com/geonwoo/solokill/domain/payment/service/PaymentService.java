package com.geonwoo.solokill.domain.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.payment.converter.PaymentConverter;
import com.geonwoo.solokill.domain.payment.dto.PayResponse;
import com.geonwoo.solokill.domain.payment.model.Payment;
import com.geonwoo.solokill.domain.payment.repository.PaymentRepository;
import com.geonwoo.solokill.global.email.EmailService;
import com.geonwoo.solokill.global.email.dto.EmailDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentRepository paymentRepository;
	private final EmailService emailService;

	@Transactional
	public PayResponse pay(Member member, Long amount) {
		Payment payment = PaymentConverter.toPayment(member, amount);
		paymentRepository.save(payment);
		PayResponse payResponse = PaymentConverter.toChargeResponse(payment);
		EmailDTO emailDTO = new EmailDTO(member.getEmail(), "Solokill 결제내역", "결제가 완료되었습니다.");
		emailService.sendEmail(emailDTO);
		return payResponse;
	}
}
