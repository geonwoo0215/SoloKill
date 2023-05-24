package com.geonwoo.solokill.domain.member.model;

import com.geonwoo.solokill.domain.member.model.vo.MemberAuthority;

import jakarta.persistence.Entity;
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

	private String loginEmail;

	private String password;

	private String nickname;

	private MemberAuthority memberAuthority;

	public Member(String loginEmail, String password, String nickname) {
		this.loginEmail = loginEmail;
		this.password = password;
		this.nickname = nickname;
		this.memberAuthority = MemberAuthority.USER;
	}
}
