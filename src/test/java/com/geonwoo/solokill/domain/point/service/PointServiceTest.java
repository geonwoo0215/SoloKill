package com.geonwoo.solokill.domain.point.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.point.dto.ChargeResponse;
import com.geonwoo.solokill.domain.point.model.Point;
import com.geonwoo.solokill.domain.point.repository.PointRepository;

@ExtendWith(MockitoExtension.class)
class PointServiceTest {

	@InjectMocks
	private PointService pointService;

	@Mock
	private PointRepository pointRepository;

	@Test
	@DisplayName("[성공] 포인트 충전에 성공한다.")
	void charge() {
		//given
		Member member = new Member("email", "password", "nickname");
		Long chargeAmount = 1000L;
		Point point = new Point(member, chargeAmount);
		when(pointRepository.save(any(Point.class))).thenReturn(point);

		//when
		ChargeResponse chargeResponse = pointService.charge(member, chargeAmount);

		//then
		assertThat(chargeResponse.currentPoint()).isEqualTo(member.getPoint());
		verify(pointRepository).save(any(Point.class));
	}
}