package com.geonwoo.solokill.domain.member.service;

public interface PasswordEncoder {

	String encrypt(String password);

	boolean isMatch(String password, String encodePassword);

}
