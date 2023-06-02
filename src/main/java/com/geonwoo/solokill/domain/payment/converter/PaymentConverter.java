package com.geonwoo.solokill.domain.payment.converter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.payment.dto.PayRequest;
import com.geonwoo.solokill.domain.payment.dto.PayResponse;
import com.geonwoo.solokill.domain.payment.model.Payment;

@Component
public class PaymentConverter {

	public static Payment toPayment(Member member, PayRequest payRequest) {
		return new Payment(member, payRequest.amount());
	}

	public static PayResponse toChargeResponse(BigDecimal amount, LocalDateTime createAt) {
		return new PayResponse(amount, createAt);
	}
}
