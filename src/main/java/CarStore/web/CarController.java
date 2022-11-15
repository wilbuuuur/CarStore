package CarStore.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import CarStore.domain.BodyRepository;
import CarStore.domain.Car;
import CarStore.domain.CarRepository;
import CarStore.domain.Feature;
import CarStore.domain.FeatureRepository;


@Controller
public class CarController {
	@Autowired
    private CarRepository carRepository; 

	@Autowired
    private FeatureRepository featureRepository;
	
	@Autowired
	private BodyRepository bodyRepository;
	
	
	@GetMapping(value = { "/main", "/" })
	public String showMainPage() {
		return "mainPage";
	}	
	
	@RequestMapping("/cars")
	public String index(Model model) {
		List<Car> cars = (List<Car>) carRepository.findAll();
		model.addAttribute("cars", cars);
    	return "cars";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCar(Model model){
    	model.addAttribute("car", new Car());
    	model.addAttribute("bodys", bodyRepository.findAll());
        return "addCar";
    }	

    @RequestMapping(value = "/edit/{id}")
    public String editCar(@PathVariable("id") Long carId, Model model){
    	model.addAttribute("car", carRepository.findById(carId));
    	model.addAttribute("bodys", bodyRepository.findAll());
        return "editCar";
    }	    
    
    @RequestMapping(value = "/saveCar", method = RequestMethod.POST)
    public String saveCar(@Valid Car car, BindingResult bindingResult){
    	
    	if (bindingResult.hasErrors()) {
    		return "addCar";
    	}
    	
        carRepository.save(car);
    	return "redirect:/cars";
    }
    
    @RequestMapping(value = "/saveEditedCar", method = RequestMethod.POST)
    public String saveEditedCar(@Valid Car car, BindingResult bindingResult){
    	
    	if (bindingResult.hasErrors()) {
    		return "editCar";
    	}
    	
        carRepository.save(car);
    	return "redirect:/cars";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCar(@PathVariable("id") Long carId, Model model) {
    	carRepository.deleteById(carId);
        return "redirect:/cars";
    }    
    
    @RequestMapping(value = "/addCarFeature/{id}", method = RequestMethod.GET)
    public String addFeature(@PathVariable("id") Long carId, Model model){

    		model.addAttribute("features", featureRepository.findAll());
    		model.addAttribute("car", carRepository.findById(carId).get());
    		return "addCarFeature";
    }
    
    
    @RequestMapping(value="/car/{id}/features", method=RequestMethod.GET)
	public String carsAddFeature(@RequestParam(value="action", required=true) String action, @PathVariable Long id, @RequestParam Long featureId, Model model) {
    	Optional<Feature> feature = featureRepository.findById(featureId);
		Optional<Car> car = carRepository.findById(id);

		if (car.isPresent() && action.equalsIgnoreCase("save")) {
			if (!car.get().hasFeature(feature.get())) {
				car.get().getFeatures().add(feature.get());
			}
			carRepository.save(car.get());
			model.addAttribute("car", featureRepository.findById(id));
			model.addAttribute("features", featureRepository.findAll());
			return "redirect:/addCarFeature/{id}";
		}

		
		return "redirect:/cars";
		
	}    
    
    @RequestMapping(value = "/getcars", method = RequestMethod.GET)
    public @ResponseBody List<Car> getCars() {
            return (List<Car>)carRepository.findAll();
    }      
}
