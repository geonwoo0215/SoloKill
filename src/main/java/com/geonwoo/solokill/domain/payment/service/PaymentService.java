package com.geonwoo.solokill.domain.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;
import com.geonwoo.solokill.domain.member.service.BCryptPasswordEncoder;
import com.geonwoo.solokill.domain.payment.converter.PaymentConverter;
import com.geonwoo.solokill.domain.payment.dto.PayRequest;
import com.geonwoo.solokill.domain.payment.dto.PayResponse;
import com.geonwoo.solokill.domain.payment.model.Payment;
import com.geonwoo.solokill.domain.payment.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final PaymentRepository paymentRepository;

	@Transactional
	public PayResponse pay(String email, PayRequest payRequest) {
		Member member = memberRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
		Payment payment = PaymentConverter.toPayment(member, payRequest);
		paymentRepository.save(payment);
		PayResponse payResponse = PaymentConverter.toChargeResponse(payRequest.amount(), payment.getCreateAt());
		return payResponse;
	}
}
