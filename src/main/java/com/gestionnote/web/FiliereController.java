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

import com.gestionnote.dao.FiliereRepository;
import com.gestionnote.dao.SemestreRepository;
import com.gestionnote.entities.Filiere;
import com.gestionnote.entities.Semestre;

@Controller
public class FiliereController {
	@Autowired
	private FiliereRepository filiereRepository;
	
	
	@GetMapping("/filieres/list")
	public ModelMap filiereList(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {   
		return new ModelMap().addAttribute("filiere", filiereRepository.findAll(pageable));

    }
	
	
	
	@GetMapping("/filieres/form")
	public String showForms(@RequestParam(value = "id", required = false) Filiere filiere, Model m) {
		if (filiere == null) {
			filiere= new Filiere();
        }
		m.addAttribute("filiere", filiere);
		return "filieres/form";
    }


	@PostMapping("/filieres/form")
	public String add(@Valid @ModelAttribute("filiere") Filiere filiere, BindingResult errors,
			SessionStatus status) {
        if (errors.hasErrors()) {
			return "filieres/form";
        }
        filiereRepository.save(filiere);
        status.setComplete();
		return "redirect:/filieres/list";
    }
	
	@GetMapping("/filieres/delete")
	public ModelMap deleteConfirm(@RequestParam(value = "id", required = true)Filiere filiere) {
		return new ModelMap("filiere", filiere);
    }



	@PostMapping("/filieres/delete")
	public Object delete(@ModelAttribute Filiere filiere, SessionStatus status) {
        try {
        	filiereRepository.delete(filiere);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")

                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/filieres/list");
        }
        status.setComplete();
		return "redirect:/filieres/list";
    }
}
