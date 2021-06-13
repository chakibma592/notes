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
import com.gestionnote.dao.SemestreRepository;

import com.gestionnote.entities.Semestre;

@Controller
public class SemestreController {
	@Autowired
	private SemestreRepository semestreRepository;
	
	
	@GetMapping("/semestres/list")
	public ModelMap semestreList(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {   
		return new ModelMap().addAttribute("semestre", semestreRepository.findAll(pageable));

    }
	
	
	
	@GetMapping("/semestres/form")
	public String showForms(@RequestParam(value = "id", required = false) Semestre semestre, Model m) {
		if (semestre == null) {
			semestre= new Semestre();
        }
		m.addAttribute("semestre", semestre);
		return "semestres/form";
    }


	@PostMapping("/semestres/form")
	public String add(@Valid @ModelAttribute("semestre") Semestre semestre, BindingResult errors,
			SessionStatus status) {
        if (errors.hasErrors()) {
			return "semestres/form";
        }
        semestreRepository.save(semestre);
        status.setComplete();
		return "redirect:/semestres/list";
    }
	
	@GetMapping("/semestres/delete")
	public ModelMap deleteConfirm(@RequestParam(value = "id", required = true)Semestre semestre) {
		return new ModelMap("semestre", semestre);
    }



	@PostMapping("/semestres/delete")
	public Object delete(@ModelAttribute Semestre semestre, SessionStatus status) {
        try {
        	semestreRepository.delete(semestre);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")

                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/semestres/list");
        }
        status.setComplete();
		return "redirect:/semestres/list";
    }
}
