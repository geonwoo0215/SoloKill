package com.geonwoo.solokill.global.firebase.service;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class FCMService {

	public void send(String token) {

		Notification notification = Notification.builder()
			.setTitle("충전 완료 알림")
			.setBody("충전이 완료되었습니다.")
			.build();

		Message message = Message.builder()
			.setNotification(notification)
			.setToken(token)
			.build();
		FirebaseMessaging.getInstance().sendAsync(message);
	}

}
