package com.gestionreseau.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gestionreseau.dao.CategoryRepository;
import com.gestionreseau.dao.CityRepository;
import com.gestionreseau.dao.PostsRepository;
import com.gestionreseau.dao.UserAccountDetailsRepository;
import com.gestionreseau.entities.Posts;

@RestController
public class PostsController {
	@Autowired
	private UserAccountDetailsRepository userAccountDetailsRepository ;

    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CityRepository cityRepository;


	@GetMapping("/posts/list")
	public ModelMap Posts(String value, Model model) {

		return new ModelMap().addAttribute("posts", postsRepository.findAll());

    }


	@GetMapping("/posts/form")
	public String showForms(@RequestParam(value = "id", required = false) Posts posts, Model m) {
		if (posts == null) {
			posts = new Posts();
        }
		m.addAttribute("posts", posts);
		m.addAttribute("category", categoryRepository.findAll());
		m.addAttribute("userAccountDetails", userAccountDetailsRepository.findAll());
		m.addAttribute("city", cityRepository.findAll());
		return "posts/form";
    }
	@PostMapping("/posts/form")
	public String add(@Valid @ModelAttribute("posts") Posts posts, BindingResult errors,
			SessionStatus status) {
        if (errors.hasErrors()) {
			return "posts/form";
        }
		postsRepository.save(posts);
        status.setComplete();
		return "redirect:/posts/list";
    }



	@GetMapping("/posts/delete")
	public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Posts posts) {
		return new ModelMap("posts", posts);
    }



	@PostMapping("/posts/delete")
	public Object delete(@ModelAttribute Posts posts, SessionStatus status) {
        try {
			postsRepository.delete(posts);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")

                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/posts/list");
        }
        status.setComplete();
		return "redirect:/posts/list";
    }

}
