package CarStore;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import CarStore.domain.Body;
import CarStore.domain.BodyRepository;
import CarStore.domain.Car;
import CarStore.domain.CarRepository;
import CarStore.domain.Feature;
import CarStore.domain.FeatureRepository;
import CarStore.domain.User;
import CarStore.domain.UserRepository;


@SpringBootApplication
public class CarStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarStoreApplication.class, args);
	}
	

	@Bean
	public CommandLineRunner demo(CarRepository carRepository, FeatureRepository featureRepository, BodyRepository bodyRepository, UserRepository userRepository) {
		return (args) -> {
			
			
			Stream.of("Steering Mounted Controls",
					"Central locking with Keyless entry",
					"Keyless Start",
					"Dual Front Airbags",
					"ABS & EBD",
					"Rear Defogger",
					"Rear Parking Sensors",
					"Front Parking Sensors",
					"Electrically Adjustable Seats",
					"Power Windows").forEach(name -> {
				featureRepository.save(new Feature(name));
			});
			
			Stream.of(
					"Pick-up",
					"Coupe",
					"Sedan",
					"Hatchback",
					"Convertible",
					"Multi-Purpose Vehicle",
					"SUV").forEach(name -> {
				bodyRepository.save(new Body(name));
			});
			
			// save cars
						carRepository.save(new Car("Toyota", "Corolla", "Red")); 
						carRepository.save(new Car("Nissan", "Qashqai", "Black"));
						carRepository.save(new Car("Audi", "A4", "Silver"));
						carRepository.save(new Car("Volvo", "XC60", "Red"));
						carRepository.save(new Car("BMW", "320", "Black"));
						carRepository.save(new Car("Audi", "A7", "Silver"));
						carRepository.save(new Car("Seat", "Leon", "Blue"));
						carRepository.save(new Car("Ford", "Mustang", "Black"));
						carRepository.save(new Car("Ford", "F150", "Blue"));
						carRepository.save(new Car("Skoda", "Fabian", "Black"));
						carRepository.save(new Car("Skoda", "Octavia", "White"));
						carRepository.save(new Car("Nissan", "200SX", "Red"));
						carRepository.save(new Car("Honda", "Civic", "Green"));
						carRepository.save(new Car("Tesla", "Model X", "Red"));
						carRepository.save(new Car("BMW", "Z4", "White"));
						carRepository.save(new Car("Dodge", "Nitro", "Brown"));
						carRepository.save(new Car("Ford", "Mondeo", "White"));

			// (user/user, admin/admin)
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
		};
	}
}
