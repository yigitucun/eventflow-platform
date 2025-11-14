package io.yigitucun.eventflow.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"io.yigitucun.eventflow.user.service",
		"io.yigitucun.eventflow.exceptions",
		"io.yigitucun.eventflow.advice",

})
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
