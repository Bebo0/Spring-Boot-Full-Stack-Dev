package com.bebo.cardatabase.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// CrudRepository provides multiple CRUD methods:
// long count() - returns number of entities
// ITerable<T> findAll()
// void delete(T entity)
// Optional<T> findById(ID Id) - returns 1 item by id
// and many more!

@RepositoryRestResource // this annotation allows us to include custom queries
public interface CarRepository extends CrudRepository <Car, Long> {

	// Fetch cars by brand
	List<Car> findByBrand(@Param("brand") String brand);
	
	// Fetch cars by brand
	List<Car> findByColor(@Param("color") String color);
		
	// Fetch cars by brand
	List<Car> findByYear(int year);
	
	// Fetch cars by brand and model
	List<Car> findByBrandAndModel(String brand, String model);
	
	
	// Fetch cars by brand or color
	List<Car> findByBrandOrColor(String brand, String color); 
	
	// Fetch cars by brand and sort by year
	List<Car> findByBrandOrderByYearAsc(String brand);

	// Fetch cars by brand using SQL
	//@Query("select c from Car c where c.brand = ?1")
	// List<Car> findByBrand(String brand);
}

//public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
//	good option if dealing with large amounts of data.
//}
