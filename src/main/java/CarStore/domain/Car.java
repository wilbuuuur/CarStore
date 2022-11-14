package CarStore.domain;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
public class Car {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Size(min=1, max=50)
	private String brand;
	
	@Size(min=1, max=50)
	private String model;
	private String color;
	private double price;
	
	@Column(name = "production_year")
	@Min(value = 1886, message = "min value is 1886")
	@Max(value = 2023, message = "max value is 2023")
	private int year;
	
    
    @ManyToOne
	@JsonIgnoreProperties("cars")
	@JoinColumn(name = "bodyid")
	private Body body;
    
    @ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "car_feature", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "featureid") })
	private Set<Feature> features = new HashSet<Feature>(0);    
    
    public Car() {
    }

	public Car(String brand, String model, String color, double price, int year) {
		super();
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.price = price;
		this.year = year;	
	}
	
	public Car(String brand, String model, String color) {
		
		super();
		Random rand  = new Random();

		this.brand = brand;
		this.model = model;
		this.color = color;
		this.price = rand.nextInt(30000) + 1;
		this.year = rand.nextInt(137) + 1886;	
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
	
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}	

	public Set<Feature> getFeatures() {
		return this.features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}
	
	public boolean hasFeature(Feature feature) {
		for (Feature carFeature: getFeatures()) {
			if (carFeature.getFeatureid() == feature.getFeatureid()) {
				return true;
			}
		}
		return false;
	}	
}
