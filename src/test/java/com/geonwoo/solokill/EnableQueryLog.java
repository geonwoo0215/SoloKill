package com.geonwoo.solokill;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import com.geonwoo.solokill.global.config.P6SpyFormatter;
import com.github.gavlyukovskiy.boot.jdbc.decorator.DataSourceDecoratorAutoConfiguration;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ImportAutoConfiguration(DataSourceDecoratorAutoConfiguration.class)
@Import(P6SpyFormatter.class)
@TestPropertySource(properties = {
	"logging.level.org.springframework.test.context=ERROR"
})
public @interface EnableQueryLog {

	@PropertyMapping("spring.jpa.show-sql")
	boolean showSql() default false;

	@PropertyMapping("decorator.datasource.p6spy.enable-logging")
	boolean enableLogging() default true;

}