package CarStore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FeatureRepository extends CrudRepository<Feature, Long>  {
    
	List<Feature> findByName(String name);
}

