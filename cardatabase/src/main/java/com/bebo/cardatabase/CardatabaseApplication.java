
package com.bebo.cardatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bebo.cardatabase.domain.Car;
import com.bebo.cardatabase.domain.CarRepository;
import com.bebo.cardatabase.domain.Owner;
import com.bebo.cardatabase.domain.OwnerRepository;
import com.bebo.cardatabase.domain.User;
import com.bebo.cardatabase.domain.UserRepository;

// this annotation is actually a combination of other annotations 
// that sets up the application for web dev.
// This is also the main class (where java typically runs its code)
@SpringBootApplication
public class CardatabaseApplication {
	// private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);
	@Autowired // this annotation is used to enable dependency injection
	private CarRepository repository;
	
	@Autowired
	private OwnerRepository orepository;
	
	@Autowired
	private UserRepository urepository;
	
	public static void main(String[] args) {
		// the DevTools dependency restarts the app whenever the file is
		// changed and saved.
		
		
		
		SpringApplication.run(CardatabaseApplication.class, args);
		// logger.info("Hello World!");// logs Hello World! in console
		// There are 7 levels of logging TRACE, DEBUG, INFO, WARN, ERROR,
		// FATAL, and OFF. Can configure level of logging in resources/application.properties file
	}
	
	// To add some demo data to our H2 database, we will use
	// CommandLineRunner to execute additional code before the app has fully started.
	@Bean
	CommandLineRunner runner() {
		return args -> {
			// Save demo data to database
			
			//Add owner objects and save these to db
			Owner owner1 = new Owner("John", "Johnson");
			Owner owner2 = new Owner("Mary" , "Robinson");
            orepository.save(owner1);
            orepository.save(owner2);

            // Add car object with link to owners and save these to db.
			Car car = new Car("Ford", "Mustang", "Red", "ADF-1121",
					2017, 59000, owner1);
			repository.save(car);
			car = new Car("Nissan", "Leaf", "White",
		             "SSJ-3002", 2014, 29000, owner2);
			repository.save(car);
			car = new Car("Toyota", "Prius", "Silver",
		             "KKO-0212", 2018, 39000, owner2);
			repository.save(car);
			
			// test users
			// username: user password: user
			urepository.save(new User("user", 
					"$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi",
					"USER"));
			// username admin password: admin
			urepository.save(new User("admin",
					"$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG",
					"ADMIN"));

			// to check that the data was saved to the Hibernate db, go to 
			// localhost:8080/h2-console then type select * from car
			
		};
	}
}
