package com.sikiapp;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

@EnableDubboConfig
@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class UserApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(UserApplication.class)
//				.web(WebApplicationType.NONE)
				.run(args);
	}
}
