package com.gestionreseau.web;

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

import com.gestionreseau.dao.CarpoolRepository;
import com.gestionreseau.dao.CategoryRepository;
import com.gestionreseau.dao.CityRepository;
import com.gestionreseau.dao.UserAccountDetailsRepository;
import com.gestionreseau.entities.Carpool;

@RestController
public class CarpoolContreller {
	@Autowired
	private CarpoolRepository carpoolRepository;
	@Autowired
	private UserAccountDetailsRepository userAccountDetailsRepository;
	@Autowired
	private CityRepository cityRepository;
	
	
	
	@GetMapping("/carpools/list")
	public ModelMap Carpool(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {   
		return new ModelMap().addAttribute("carpool", carpoolRepository.findAll(pageable));

    }
	
	@PostMapping("/carpools/insert")
	public Carpool carpool(@Valid @RequestBody Carpool carpool) {
		return carpoolRepository.save(carpool);
	}
	
	@GetMapping("/carpools/form")
	public String showForms(@RequestParam(value = "id", required = false) Carpool carpool, Model m) {
		if (carpool == null) {
			carpool = new Carpool();
        }
		m.addAttribute("carpool", carpool);
		m.addAttribute("carpool", carpoolRepository.findAll());
		m.addAttribute("city", cityRepository.findAll());
		m.addAttribute("userAccountDetails", userAccountDetailsRepository.findAll());
		return "carpools/form";
    }


	@PostMapping("/carpools/form")
	public String add(@Valid @ModelAttribute("category") Carpool carpool, BindingResult errors,
			SessionStatus status) {
        if (errors.hasErrors()) {
			return "categories/form";
        }
        carpoolRepository.save(carpool);
        status.setComplete();
		return "redirect:/carpools/list";
    }
	
	@GetMapping("/carpools/delete")
	public ModelMap deleteConfirm(@RequestParam(value = "id", required = true)Carpool carpool) {
		return new ModelMap("carpool", carpool);
    }



	@PostMapping("/carpools/delete")
	public Object delete(@ModelAttribute Carpool carpool, SessionStatus status) {
        try {
        	carpoolRepository.delete(carpool);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")

                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/carpools/list");
        }
        status.setComplete();
		return "redirect:/carpools/list";
    }

}
