package com.geonwoo.solokill.domain.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.service.BCryptPasswordEncoder;
import com.geonwoo.solokill.domain.payment.converter.PaymentConverter;
import com.geonwoo.solokill.domain.payment.dto.PayResponse;
import com.geonwoo.solokill.domain.payment.model.Payment;
import com.geonwoo.solokill.domain.payment.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final PaymentRepository paymentRepository;

	@Transactional
	public PayResponse pay(Member member, Long amount) {
		Payment payment = PaymentConverter.toPayment(member, amount);
		paymentRepository.save(payment);
		PayResponse payResponse = PaymentConverter.toChargeResponse(payment);
		return payResponse;
	}
}
