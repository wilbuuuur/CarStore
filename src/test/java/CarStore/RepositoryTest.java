package CarStore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import CarStore.domain.Body;
import CarStore.domain.BodyRepository;
import CarStore.domain.Car;
import CarStore.domain.CarRepository;
import CarStore.domain.User;
import CarStore.domain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@ExtendWith(SpringExtension.class)
@DataJpaTest


public class RepositoryTest {
	
	@Autowired
	private CarRepository carRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BodyRepository bodyRepo;
	
	@Test
	public void createNewCar() {
    	Car car = new Car("testi", "testi", "testi");
    	carRepo.save(car);
    	assertThat(car.getId()).isNotNull();
    }
	@Test
	public void createNewBody() {
    	Body body = new Body("testi");
    	bodyRepo.save(body);
    	assertThat(body.getBodyid()).isNotNull();
    }
	
	@Test
	public void createNewUser() {
		User user = new User("testi", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
		userRepo.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test  
    public void findByBrand() {
        List<Car> cars = carRepo.findByBrand("Toyota");
        
        assertThat(cars).hasSize(1);
        assertThat(cars.get(0).getBrand()).isEqualTo("Toyota");
    }
	
	@Test  
    public void findByBodyName() {
        List<Body> bodys = bodyRepo.findByName("Pick-up");
        
        assertThat(bodys).hasSize(1);
        assertThat(bodys.get(0).getName()).isEqualTo("Pick-up");
    }
	
	@Test
	public void findByUserName() {
        User user = userRepo.findByUsername("admin");
       
        assertThat(user.getRole()).isEqualTo("ADMIN");
    }
	
	@Test
	public void deleteCar() {
		carRepo.delete(carRepo.findByBrand("Honda").get(0));
		List<Car> cars = carRepo.findByBrand("Honda");
		assertThat(cars).hasSize(0);
	}
	
	@Test
	public void deleteBody() {
		bodyRepo.delete(bodyRepo.findByName("Coupe").get(0));
		List<Body> bodys = bodyRepo.findByName("Coupe");
		assertThat(bodys).hasSize(0);
	}
	
	@Test
    public void deleteUser() {
        userRepo.delete(userRepo.findByUsername("user"));
        User user = userRepo.findByUsername("user");
        assertThat(user).isNull();    
    }
	
	@Test
	public void saveCar() {
		Car car = new Car("Volkswagen", "Gof", "Silver");
		carRepo.save(car);
		assertThat(car.getId()).isNotNull();
	}

}
