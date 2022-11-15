package CarStore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findByBrand(String brand);
    List<Car> findByBodyId(Long id);
	List<Car> findByBodyName(String lastname);
  
    
}
