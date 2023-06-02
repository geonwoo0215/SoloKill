package com.geonwoo.solokill.domain.point.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.point.dto.ChargeResponse;
import com.geonwoo.solokill.domain.point.model.Point;
import com.geonwoo.solokill.domain.point.repository.PointRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointService {

	private final PointRepository pointRepository;

	@Transactional
	public ChargeResponse charge(Member member, Long chargeAmount) {
		Point point = new Point(member, chargeAmount);
		pointRepository.save(point);
		member.chargePoint(chargeAmount);
		return new ChargeResponse(member.getPoint());
	}
	
}
