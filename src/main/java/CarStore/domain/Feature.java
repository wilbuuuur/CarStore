package CarStore.domain;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Feature {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)	
	private Long featureid;
	
    @Column(name="featurename")
    @Size(min=1, max=50)
	private String name; 
     
    @ManyToMany(mappedBy = "features")    
    private Set<Car> cars;  

    public Feature() {
	}

	public Feature(String name) {
		this.name = name;
	}     

    public Long getFeatureid() {
		return featureid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
    
    
    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }	
}
