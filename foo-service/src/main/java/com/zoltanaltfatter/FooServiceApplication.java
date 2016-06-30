package com.zoltanaltfatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class FooServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FooServiceApplication.class, args);
	}
}


@RestController
@RefreshScope
class MyRestController {

	public MyRestController() {
		System.out.println(getClass().getName() + " re-initialized");
	}

	@Value("${message}")
	private String message;

	@Value("${secret-message}")
	private String secretMessage;

	@RequestMapping("/message")
	String message() {
		return message;
	}

	@RequestMapping("/secret-message")
	String secretMessage() {
		return secretMessage;
	}

}

@RestController
class AnotherRestController {

	public AnotherRestController() {
		System.out.println(getClass().getName() + " re-initialized");
	}

	@Value("${message}")
	private String message;

	@RequestMapping("/message2")
	String message() {
		return message;
	}

}

@RestController
class ThirdRestController {

	MessageService messageService;

	@Autowired
	ThirdRestController(MessageService messageService) {
		this.messageService = messageService;
		System.out.println(getClass().getName() + " re-initialized");
	}

	@RequestMapping("/message3")
	String message() {
		return messageService.getMessage();
	}

}

@Service
@RefreshScope
class MessageService {

	MessageService() {
		System.out.println(getClass().getName() + " re-initialized");
	}

	@Value("${message}")
	String message;

	String getMessage() {
		return message;
	}
}