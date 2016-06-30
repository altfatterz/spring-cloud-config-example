package com.zoltanaltfatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BazServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BazServiceApplication.class, args);
	}
}


@RestController
@RefreshScope
class MyRestController {

	@Value("${message}")
	private String message;

	@RequestMapping("/message")
	String message() {
		return message;
	}

}