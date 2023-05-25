package com.geonwoo.solokill.global.security;

import static com.geonwoo.solokill.domain.member.model.vo.MemberAuthority.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.geonwoo.solokill.domain.member.model.vo.MemberAuthority;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

	MemberAuthority role() default USER;

	MemberAuthority[] allowedRoles() default {USER, ADMIN};
}
