package com.gestionreseau.web;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.gestionreseau.dao.NaturePIDao;
import com.gestionreseau.entities.NaturePI;


@Controller
public class NaturePIController {

    @Autowired
	private NaturePIDao naturepidao;

	@GetMapping("/NaturePI/list")
	public ModelMap unite(String value, Model model) {
      
		return new ModelMap().addAttribute("data", naturepidao.findAll());

    }

	@GetMapping("/NaturePI/form")
	public String showForms(@RequestParam(value = "id", required = false) NaturePI naturepi, Model m) {
		if (naturepi == null) {
			naturepi = new NaturePI();
        }
		m.addAttribute("naturepi", naturepi);
		return "NaturePI/form";
    }
	@PostMapping("/NaturePI/form")
	public String save(@Valid @ModelAttribute("naturepi") NaturePI naturepi, BindingResult errors,
			SessionStatus status) {
        if (errors.hasErrors()) {
			return "NaturePI/form";
        }
		naturepidao.save(naturepi);
        status.setComplete();
		return "redirect:/NaturePI/list";
    }

	@GetMapping("/NaturePI/delete")
	public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) NaturePI naturepi) {
		return new ModelMap("naturepi", naturepi);
    }

	@PostMapping("/NaturePI/delete")
	public Object delete(@ModelAttribute NaturePI naturepi, SessionStatus status) {
        try {
			naturepidao.delete(naturepi);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
					.addObject("entityName", "naturepi")
                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/NaturePI/list");
        }
        status.setComplete();
		return "redirect:/NaturePI/list";
    }
}