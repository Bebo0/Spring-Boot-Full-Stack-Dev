package com.bebo.cardatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bebo.cardatabase.domain.Car;
import com.bebo.cardatabase.domain.CarRepository;

@RestController
public class CarController {
	
	@Autowired
	private CarRepository repository;
	
	// By default, @RequestMapping handles all HTTP methods (GET PUT POST etc.)
	// You can define which method is accepted with the following:
	// @RequestMapping("/cars", method=GET). Now this method handles only GET requests
	// from the /cars endpoint.
	@RequestMapping("/cars") // When a user navigates to the /cars endpoint, getCars() is executed.
	public Iterable<Car> getCars() {
		return repository.findAll();
		
		
		
		
		
		
		
	}
		
}
