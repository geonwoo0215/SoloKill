package com.geonwoo.solokill.domain.member.exception;

public class MemberNotFoundException extends RuntimeException {

    private static final String MESSAGE = "사용자를 찾을 수 없습니다.";

    public MemberNotFoundException() {
        super(MESSAGE);
    }
}
