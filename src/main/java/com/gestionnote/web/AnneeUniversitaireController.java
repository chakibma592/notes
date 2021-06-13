package com.gestionnote.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gestionnote.dao.AnneeUniversitaireRepository;
import com.gestionnote.dao.SemestreRepository;
import com.gestionnote.entities.AnneeUniversitaire;
import com.gestionnote.entities.Semestre;

@Controller
public class AnneeUniversitaireController {
	@Autowired
	private AnneeUniversitaireRepository yearRepository;
	
	
	@GetMapping("/year/list")
	public ModelMap yearList(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {   
		return new ModelMap().addAttribute("year", yearRepository.findAll(pageable));

    }
	
	
	
	@GetMapping("/year/form")
	public String showForms(@RequestParam(value = "id", required = false) AnneeUniversitaire year, Model m) {
		if (year== null) {
			year= new AnneeUniversitaire();
        }
		m.addAttribute("year", year);
		return "year/form";
    }


	@PostMapping("/year/form")
	public String add(@Valid @ModelAttribute("year") AnneeUniversitaire year, BindingResult errors,
			SessionStatus status) {
        if (errors.hasErrors()) {
			return "year/form";
        }
        yearRepository.save(year);
        status.setComplete();
		return "redirect:/year/list";
    }
	
	@GetMapping("/year/delete")
	public ModelMap deleteConfirm(@RequestParam(value = "id", required = true)AnneeUniversitaire year) {
		return new ModelMap("year", year);
    }



	@PostMapping("/year/delete")
	public Object delete(@ModelAttribute AnneeUniversitaire year, SessionStatus status) {
        try {
        	yearRepository.delete(year);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")

                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/year/list");
        }
        status.setComplete();
		return "redirect:/year/list";
    }
}
