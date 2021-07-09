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
import com.gestionnote.dao.ModuleRepository;
import com.gestionnote.dao.SemestreDescribeRepository;
import com.gestionnote.dao.SemestreRepository;
import com.gestionnote.entities.SemestreDescribe;

@Controller
public class SemestreDescribeController {
	@Autowired
	private SemestreDescribeRepository semestreDescribeRepository;
    @Autowired
    private FiliereRepository filiereRepository;
    @Autowired
    private SemestreRepository semestreRepository;
    @Autowired
    private ModuleRepository moduleRepository;
	
	@GetMapping("/semestredescribes/list")
	public ModelMap semestreList(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {   
		return new ModelMap().addAttribute("semestredescribe", semestreDescribeRepository.findAll(pageable));

    }
	
	
	
	@GetMapping("/semestredescribes/form")
	public String showForms(@RequestParam(value = "id", required = false) SemestreDescribe semestre, Model m) {
		if (semestre == null) {
			semestre= new SemestreDescribe();
        }
		m.addAttribute("semestredescribe", semestre);
		m.addAttribute("semestre", semestreRepository.findAll());
		m.addAttribute("module",moduleRepository.findAll());
		m.addAttribute("filiere", filiereRepository.findAll());
		return "semestredescribes/form";
    }


	@PostMapping("/semestredescribes/form")
	public String add(@Valid @ModelAttribute("semestredescribe") SemestreDescribe semestre, BindingResult errors,
			SessionStatus status) {
        if (errors.hasErrors()) {
			return "semestredescribes/form";
        }
        semestreDescribeRepository.save(semestre);
        status.setComplete();
		return "redirect:/semestredescribes/list";
    }
	
	@GetMapping("/semestredescribes/delete")
	public ModelMap deleteConfirm(@RequestParam(value = "id", required = true)SemestreDescribe semestre) {
		return new ModelMap("semestredescribe", semestre);
    }



	@PostMapping("/semestredescribes/delete")
	public Object delete(@ModelAttribute SemestreDescribe semestre, SessionStatus status) {
        try {
        	semestreDescribeRepository.delete(semestre);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")

                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/semestredescribes/list");
        }
        status.setComplete();
		return "redirect:/semestredescribes/list";
    }

}
