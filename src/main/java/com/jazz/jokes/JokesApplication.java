package com.jazz.jokes;

import com.jazz.jokes.services.Twilo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JokesApplication {

	public static void main(String[] args) {
		// Start the Spring application context
		ApplicationContext context = SpringApplication.run(JokesApplication.class, args);

		// Retrieve the Twilo bean from the context
		Twilo twilo = context.getBean(Twilo.class);

		// Use the bean
		twilo.sendMessage();
	}

}
