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

import com.gestionnote.dao.MatiereRepository;
import com.gestionnote.dao.ModuleRepository;
import com.gestionnote.entities.Matiere;

@Controller
public class MatiereController {

    @Autowired
	private MatiereRepository matiereRepository;

    @Autowired
    private ModuleRepository moduleRepository;


	@GetMapping("/matieres/list")
	public ModelMap matiereList(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {   
		return new ModelMap().addAttribute("matiere", matiereRepository.findAll(pageable));

    }
	

	@GetMapping("/matieres/form")
	public String showForms(@RequestParam(value = "id", required = false) Matiere matiere, Model m) {
		if (matiere == null) {
			matiere = new Matiere();
        }
		m.addAttribute("matiere", matiere);
		m.addAttribute("module", moduleRepository.findAll());
		return "matieres/form";
    }


	@PostMapping("/matieres/form")
	public String add(@Valid @ModelAttribute("matiere") Matiere matiere, BindingResult errors,
			SessionStatus status) {
        if (errors.hasErrors()) {
			return "matiere/form";
        }
		matiereRepository.save(matiere);
        status.setComplete();
		return "redirect:/matieres/list";
    }



	@GetMapping("/matieres/delete")
	public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Matiere matiere) {
		return new ModelMap("matiere", matiere);
    }



	@PostMapping("/matieres/delete")
	public Object delete(@ModelAttribute Matiere matiere, SessionStatus status) {
        try {
			matiereRepository.delete(matiere);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")

                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/matieres/list");
        }
        status.setComplete();
		return "redirect:/matieres/list";
    }
}