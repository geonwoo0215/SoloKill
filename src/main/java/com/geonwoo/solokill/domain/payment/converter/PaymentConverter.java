package com.geonwoo.solokill.domain.payment.converter;

import org.springframework.stereotype.Component;

import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.payment.dto.PayResponse;
import com.geonwoo.solokill.domain.payment.model.Payment;

@Component
public class PaymentConverter {

	public static Payment toPayment(Member member, Long amount) {
		return new Payment(member, amount);
	}

	public static PayResponse toChargeResponse(Payment payment) {
		return new PayResponse(payment.getId(), payment.getValue(), payment.getCreateAt());
	}
}
