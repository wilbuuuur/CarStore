package CarStore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import CarStore.web.BodyController;
import CarStore.web.CarController;
import CarStore.web.FeatureController;

@SpringBootTest
class CarStoreApplicationTests {
	
	@Autowired
	private CarController carController;
	
	@Autowired
	private BodyController bodyController;
	
	@Autowired
	private FeatureController featureController;
	

	@Test
	void contextLoads() {
		assertThat(carController).isNotNull();
		assertThat(bodyController).isNotNull();
		assertThat(featureController).isNotNull();
	}
	}


