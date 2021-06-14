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

import com.gestionnote.dao.ModuleRepository;
import com.gestionnote.dao.SemestreRepository;
import com.gestionnote.entities.Module;
import com.gestionnote.entities.Semestre;

@Controller
public class ModuleController {
	@Autowired
	private ModuleRepository moduleRepository;
	
	
	@GetMapping("/modules/list")
	public ModelMap semestreList(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {   
		return new ModelMap().addAttribute("module", moduleRepository.findAll(pageable));

    }
	
	
	
	@GetMapping("/modules/form")
	public String showForms(@RequestParam(value = "id", required = false) Module module, Model m) {
		if (module == null) {
			module= new Module();
        }
		m.addAttribute("module", module);
		return "modules/form";
    }


	@PostMapping("/modules/form")
	public String add(@Valid @ModelAttribute("module") Module module, BindingResult errors,
			SessionStatus status) {
        if (errors.hasErrors()) {
			return "modules/form";
        }
        moduleRepository.save(module);
        status.setComplete();
		return "redirect:/modules/list";
    }
	
	@GetMapping("/modules/delete")
	public ModelMap deleteConfirm(@RequestParam(value = "id", required = true)Module module) {
		return new ModelMap("module", module);
    }



	@PostMapping("/modules/delete")
	public Object delete(@ModelAttribute Module module, SessionStatus status) {
        try {
        	moduleRepository.delete(module);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")

                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/modules/list");
        }
        status.setComplete();
		return "redirect:/modules/list";
    }
}
