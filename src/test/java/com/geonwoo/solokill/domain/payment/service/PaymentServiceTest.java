package com.geonwoo.solokill.domain.payment.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;
import com.geonwoo.solokill.domain.payment.dto.PayRequest;
import com.geonwoo.solokill.domain.payment.dto.PayResponse;
import com.geonwoo.solokill.domain.payment.model.Payment;
import com.geonwoo.solokill.domain.payment.repository.PaymentRepository;

@ExtendWith({MockitoExtension.class})
class PaymentServiceTest {

	@InjectMocks
	private PaymentService paymentService;

	@Mock
	private MemberRepository memberRepository;

	@Mock
	private PaymentRepository paymentRepository;

	@Test
	@DisplayName("[성공] 결제에 성공한다.")
	void pay() {

		//given
		Member member = new Member("email", "password", "nickname");
		memberRepository.save(member);
		PayRequest payRequest = new PayRequest(1000L);
		Payment payment = new Payment(member, payRequest.amount());

		when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

		//when
		PayResponse pay = paymentService.pay(member, payRequest.amount());

		//then
		assertThat(pay)
			.hasFieldOrPropertyWithValue("amount", payRequest.amount());
		verify(paymentRepository).save(any(Payment.class));
	}
}