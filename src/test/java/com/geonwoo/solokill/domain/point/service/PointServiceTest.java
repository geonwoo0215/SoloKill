package com.geonwoo.solokill.domain.point.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;
import com.geonwoo.solokill.domain.payment.dto.PayResponse;
import com.geonwoo.solokill.domain.payment.service.PaymentService;
import com.geonwoo.solokill.domain.point.model.Point;
import com.geonwoo.solokill.domain.point.repository.PointRepository;

@ExtendWith(MockitoExtension.class)
class PointServiceTest {

	@InjectMocks
	private PointService pointService;

	@Mock
	private PaymentService paymentService;

	@Mock
	private PointRepository pointRepository;

	@Mock
	private MemberRepository memberRepository;

	@Test
	@DisplayName("[성공] 포인트 충전에 성공한다.")
	void charge() {
		//given
		Member member = new Member("email", "password", "nickname");
		Long chargeAmount = 1000L;
		Point point = new Point(member, chargeAmount);
		PayResponse payResponse = new PayResponse(1L, chargeAmount, LocalDateTime.now());
		when(pointRepository.save(any(Point.class))).thenReturn(point);
		when(memberRepository.findByEmail(member.getEmail())).thenReturn(Optional.of(member));
		when(paymentService.pay(member, chargeAmount)).thenReturn(payResponse);

		//when
		pointService.charge(member.getEmail(), point.getValue());

		//then
		assertThat(member.getPoint()).isEqualTo(chargeAmount);
		verify(memberRepository).findByEmail(member.getEmail());
		verify(pointRepository).save(any(Point.class));
		verify(paymentService).pay(member, chargeAmount);
	}
}