package CarStore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import CarStore.domain.Car;
import CarStore.domain.CarRepository;



@RestController
public class CarRestController {

	@Autowired
	CarRepository carRepository;

	// list of cars
	@GetMapping("/rest/cars")
	public Iterable<Car> getCars() {
		
		return carRepository.findAll();
	}

	// add new car
	@PostMapping("rest/cars")
	Car newCar(@RequestBody Car newCar) {
		return carRepository.save(newCar);
	}

	// edit existing car
	@PutMapping("/rest/cars/{id}")
	Car editCar(@RequestBody Car editedCar, @PathVariable Long id) {
		editedCar.setId(id);
		return carRepository.save(editedCar);

	}

	// delete car
	@DeleteMapping("/rest/cars/{id}")
	void deleteCar(@PathVariable Long id) {
		carRepository.deleteById(id);
	}

}
