package com.geonwoo.solokill.domain.point.service;

import com.geonwoo.solokill.domain.member.repository.MemberRepository;
import com.geonwoo.solokill.domain.payment.service.PaymentService;
import com.geonwoo.solokill.domain.point.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;
    private final PaymentService paymentService;
    private final MemberRepository memberRepository;

}
