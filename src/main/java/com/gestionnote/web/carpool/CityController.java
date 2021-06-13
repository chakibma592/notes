package com.gestionnote.web.carpool;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gestionnote.dao.carpool.CityRepository;
import com.gestionnote.entities.carpool.City;

@RestController
public class CityController {
	@Autowired
	private CityRepository cityRepository;
	
	
	@GetMapping("/cities/list")
	public ModelMap City(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {   
		return new ModelMap().addAttribute("city", cityRepository.findAll(pageable));

    }
	
	@PostMapping("/cities/insert")
	public City city(@Valid @RequestBody City city) {
		return cityRepository.save(city);
	}
	
	@GetMapping("/cities/form")
	public String showForms(@RequestParam(value = "id", required = false) City city, Model m) {
		if (city == null) {
			city= new City();
        }
		m.addAttribute("city", city);
		m.addAttribute("city", cityRepository.findAll());
		return "cities/form";
    }


	@PostMapping("/cities/form")
	public String add(@Valid @ModelAttribute("Category") City city, BindingResult errors,
			SessionStatus status) {
        if (errors.hasErrors()) {
			return "cities/form";
        }
        cityRepository.save(city);
        status.setComplete();
		return "redirect:/cities/list";
    }
	
	@GetMapping("/cities/delete")
	public ModelMap deleteConfirm(@RequestParam(value = "id", required = true)City city) {
		return new ModelMap("city", city);
    }



	@PostMapping("/cities/delete")
	public Object delete(@ModelAttribute City city, SessionStatus status) {
        try {
        	cityRepository.delete(city);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")

                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/cities/list");
        }
        status.setComplete();
		return "redirect:/cities/list";
    }

}
