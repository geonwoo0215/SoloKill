package com.geonwoo.solokill.domain.point.service;

import com.geonwoo.solokill.domain.member.repository.MemberRepository;
import com.geonwoo.solokill.domain.payment.service.PaymentService;
import com.geonwoo.solokill.domain.point.repository.PointRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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


}