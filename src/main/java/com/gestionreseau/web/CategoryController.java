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
import com.gestionreseau.dao.CategoryRepository;
import com.gestionreseau.entities.Category;

@RestController
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@GetMapping("/categories/list")
	public ModelMap Category(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {   
		return new ModelMap().addAttribute("category", categoryRepository.findAll(pageable));

    }
	
	@PostMapping("/categories/insert")
	public Category category(@Valid @RequestBody Category category) {
		return categoryRepository.save(category);
	}
	
	@GetMapping("/categories/form")
	public String showForms(@RequestParam(value = "id", required = false) Category category, Model m) {
		if (category == null) {
			category = new Category();
        }
		m.addAttribute("category", category);
		m.addAttribute("category", categoryRepository.findAll());
		return "categories/form";
    }


	@PostMapping("/categories/form")
	public String add(@Valid @ModelAttribute("category") Category category, BindingResult errors,
			SessionStatus status) {
        if (errors.hasErrors()) {
			return "categories/form";
        }
        categoryRepository.save(category);
        status.setComplete();
		return "redirect:/categories/list";
    }
	
	@GetMapping("/categories/delete")
	public ModelMap deleteConfirm(@RequestParam(value = "id", required = true)Category category) {
		return new ModelMap("category", category);
    }



	@PostMapping("/categories/delete")
	public Object delete(@ModelAttribute Category category, SessionStatus status) {
        try {
        	categoryRepository.delete(category);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")

                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/categories/list");
        }
        status.setComplete();
		return "redirect:/categories/list";
    }

}
