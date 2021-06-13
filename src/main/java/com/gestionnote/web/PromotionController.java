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

import com.gestionnote.dao.PromotionRepository;
import com.gestionnote.entities.Promotion;


@Controller
public class PromotionController {
	@Autowired
	private PromotionRepository promotionRepository;
	
	
	@GetMapping("/promotions/list")
	public ModelMap promotionList(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {   
		return new ModelMap().addAttribute("promotion", promotionRepository.findAll(pageable));

    }
	
	
	
	@GetMapping("/promotions/form")
	public String showForms(@RequestParam(value = "id", required = false) Promotion promotion, Model m) {
		if (promotion == null) {
			promotion= new Promotion();
        }
		m.addAttribute("promotion", promotion);
		return "promotions/form";
    }


	@PostMapping("/promotions/form")
	public String add(@Valid @ModelAttribute("promotion") Promotion promotion, BindingResult errors,
			SessionStatus status) {
        if (errors.hasErrors()) {
			return "promotions/form";
        }
        promotionRepository.save(promotion);
        status.setComplete();
		return "redirect:/promotions/list";
    }
	
	@GetMapping("/promotions/delete")
	public ModelMap deleteConfirm(@RequestParam(value = "id", required = true)Promotion promotion) {
		return new ModelMap("promotion", promotion);
    }



	@PostMapping("/promotions/delete")
	public Object delete(@ModelAttribute Promotion promotion, SessionStatus status) {
        try {
        	promotionRepository.delete(promotion);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")

                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/promotions/list");
        }
        status.setComplete();
		return "redirect:/promotions/list";
    }
}
