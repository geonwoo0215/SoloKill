package com.geonwoo.solokill.domain.member.model;

import com.geonwoo.solokill.domain.member.model.vo.MemberAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String password;

	private String nickname;

	private Long point;

	@Enumerated(value = EnumType.STRING)
	private MemberAuthority memberAuthority;

	public Member(String email, String password, String nickname) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.point = 0L;
		this.memberAuthority = MemberAuthority.USER;
	}

	public void chargePoint(Long chargeAmount) {
		this.point += chargeAmount;
	}
}
