package com.geonwoo.solokill.domain.payment.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.geonwoo.solokill.domain.payment.dto.event.PushAlertEvent;
import com.geonwoo.solokill.global.firebase.service.FCMService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentEventHandler {

	private final FCMService fcmService;

	@EventListener
	public void pushAlert(PushAlertEvent pushAlertEvent) {
		fcmService.send(pushAlertEvent.token());
	}
}
