package com.geonwoo.solokill.domain.point.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geonwoo.solokill.domain.member.model.Member;
import com.geonwoo.solokill.domain.member.repository.MemberRepository;
import com.geonwoo.solokill.domain.payment.dto.PayResponse;
import com.geonwoo.solokill.domain.payment.service.PaymentService;
import com.geonwoo.solokill.domain.point.dto.ChargeResponse;
import com.geonwoo.solokill.domain.point.model.Point;
import com.geonwoo.solokill.domain.point.repository.PointRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointService {

	private final PointRepository pointRepository;
	private final PaymentService paymentService;
	private final MemberRepository memberRepository;

	@Transactional
	public ChargeResponse charge(String email, String token, Long chargeAmount) {
		Member member = memberRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
		PayResponse payResponse = paymentService.pay(member, chargeAmount, token);
		Point point = new Point(member, chargeAmount);
		pointRepository.save(point);
		member.chargePoint(chargeAmount);
		return new ChargeResponse(payResponse.id(), chargeAmount, member.getPoint());
	}

}
