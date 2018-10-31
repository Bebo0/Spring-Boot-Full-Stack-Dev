
package com.bebo.cardatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// this annotation is actually a combination of other annotations 
// that sets up the application for web dev.
// This is also the main class (where java typically runs its code)
@SpringBootApplication
public class CardatabaseApplication {
	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	public static void main(String[] args) {
		// the DevTools dependency restarts the app whenever the file is
		// changed and saved.
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Hello World!");
	}
}
