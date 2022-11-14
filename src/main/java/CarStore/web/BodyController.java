package CarStore.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import CarStore.domain.Body;
import CarStore.domain.BodyRepository;
import CarStore.domain.CarRepository;



@Controller
public class BodyController {

	@Autowired
	private BodyRepository bodyRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@RequestMapping(value = "/bodylist", method = RequestMethod.GET)
	public String getBodys(Model model) {
		model.addAttribute("bodys", bodyRepository.findAll());
		return "bodylist";
	}
		
		@RequestMapping(value = "/addbody", method = RequestMethod.GET)
	    public String addBody(Model model){
	    	model.addAttribute("body", new Body());
	    	return "addbody";
	}
		
		@RequestMapping(value = "/savebody")
	    public String saveBody(@Valid Body body, BindingResult bindingResult){
			
			if (bindingResult.hasErrors()) {
	    		return "addbody";
	    	}
			
	        bodyRepository.save(body);
	        return "redirect:/bodylist";
	    }
		
		@GetMapping("deletebody/{id}")
		public String deleteCar(@PathVariable("id") Long id, Model model) {
			bodyRepository.deleteById(id);
			return "redirect:/bodylist";
		}
}
