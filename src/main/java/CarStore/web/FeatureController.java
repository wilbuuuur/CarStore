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

import CarStore.domain.Feature;
import CarStore.domain.FeatureRepository;

@Controller
public class FeatureController {
	
	@Autowired
	private FeatureRepository featureRepository;
	
	
	@RequestMapping(value = "/featurelist", method = RequestMethod.GET)
	public String getFeatures(Model model) {
		model.addAttribute("features", featureRepository.findAll());
		return "featurelist";
	}
		
		@RequestMapping(value = "/addfeature", method = RequestMethod.GET)
	    public String addFeature(Model model){
	    	model.addAttribute("feature", new Feature());
	    	return "addfeature";
	}
		
		@RequestMapping(value = "/savefeature")
	    public String saveFeature(@Valid Feature feature, BindingResult bindingResult){
			
			if (bindingResult.hasErrors()) {
	    		return "addfeature";
	    	}
			
	        featureRepository.save(feature);
	        return "redirect:/featurelist";
	    }
		
		@GetMapping("deletefeature/{id}")
		public String deleteFeature(@PathVariable("id") Long id, Model model) {
			featureRepository.deleteById(id);
			return "redirect:/featurelist";
		}
}
