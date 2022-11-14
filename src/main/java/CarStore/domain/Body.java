package CarStore.domain;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Body {
	@Id
	@JsonIgnoreProperties("body")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bodyid;
	
	@Size(min=1, max=50)
	private String name;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy ="body")
	private List<Car> cars;

	public Body() {}

	public Body(String name) {
		super();
		this.name = name;
	}

	public Long getBodyid() {
		return bodyid;
	}

	public void setBodyid(Long bodyid) {
		this.bodyid = bodyid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "Category [bodyid=" + bodyid + ", name=" + name + "]";
	}
	
	

}

