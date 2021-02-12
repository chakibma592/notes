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

import com.gestionreseau.entities.UserAccountDetails ;


import com.gestionreseau.dao.PostsRepository;
import com.gestionreseau.dao.UserAccountDetailsRepository;
import com.gestionreseau.entities.PointInteret;
import com.gestionreseau.entities.Post;

@RestController
public class UserAccountDetailsController {
	@Autowired
	private UserAccountDetailsRepository userAccountDetailsRepository;
	
	
	@GetMapping("/useraccountdetails/list")
	public ModelMap UserAccountDetails(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {   
		return new ModelMap().addAttribute("useraccountdetails", userAccountDetailsRepository.findAll(pageable));

    }
	
	@PostMapping("/useraccountdetails/insert")
	public UserAccountDetails createUserAccountDetails(@Valid @RequestBody UserAccountDetails userAccountDetails) {
		return userAccountDetailsRepository.save(userAccountDetails);
	}
	
	@GetMapping("/useraccountdetails/form")
	public String showForms(@RequestParam(value = "id", required = false) UserAccountDetails userAccountDetails, Model m) {
		if (userAccountDetails == null) {
			userAccountDetails = new UserAccountDetails();
        }
		m.addAttribute("userAccountDetails", userAccountDetails);
		m.addAttribute("userAccountDetails", userAccountDetailsRepository.findAll());
		return "useraccountdetails/form";
    }


	@PostMapping("/useraccountdetails/form")
	public String add(@Valid @ModelAttribute("userAccountDetails") UserAccountDetails userAccountDetails, BindingResult errors,
			SessionStatus status) {
        if (errors.hasErrors()) {
			return "useraccountdetails/form";
        }
        userAccountDetailsRepository.save(userAccountDetails);
        status.setComplete();
		return "redirect:/useraccountdetails/list";
    }
	
	@GetMapping("/useraccountdetails/delete")
	public ModelMap deleteConfirm(@RequestParam(value = "id", required = true)UserAccountDetails userAccountDetails) {
		return new ModelMap("userAccountDetails", userAccountDetails);
    }



	@PostMapping("/useraccountdetails/delete")
	public Object delete(@ModelAttribute UserAccountDetails userAccountDetails, SessionStatus status) {
        try {
			userAccountDetailsRepository.delete(userAccountDetails);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")

                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/useraccountdetails/list");
        }
        status.setComplete();
		return "redirect:/useraccountdetails/list";
    }
}
