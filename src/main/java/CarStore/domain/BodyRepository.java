package CarStore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface BodyRepository extends CrudRepository<Body, Long>{

	List<Body> findByName(String name);
	
}

