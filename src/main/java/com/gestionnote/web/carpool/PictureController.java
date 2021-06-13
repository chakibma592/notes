package com.gestionnote.web.carpool;

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

import com.gestionnote.dao.carpool.NaturePIDao;
import com.gestionnote.dao.carpool.PictureDao;
import com.gestionnote.dao.carpool.PointInteretDao;
import com.gestionnote.dao.carpool.PostsRepository;
import com.gestionnote.entities.carpool.Picture;


@Controller
public class PictureController {
	
    @Autowired
    private PictureDao pictureDao;
    @Autowired
   	private PointInteretDao pointInteretDao;
    @Autowired
   	private PostsRepository postsRepository;

	@GetMapping("/picture/list")
	public ModelMap picture(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {
   
		return new ModelMap().addAttribute("picture", pictureDao.findAll(pageable));

    }

	@GetMapping("/picture/form")
    public String showForm(@RequestParam(value = "id", required = false) Picture picture, Model m ) {
        if (picture == null) {
            picture = new Picture();
        }
        m.addAttribute("picture", picture);
        m.addAttribute("pointinteret", pointInteretDao.findAll());
        m.addAttribute("posts", postsRepository.findAll());
        
        return "picture/form";
    }

	@PostMapping("/picture/form")
	public String save(@Valid @ModelAttribute("picture") Picture picture, BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
			return "picture/form";
        }
        pictureDao.save(picture);
        status.setComplete();
		return "redirect:/picture/list";
    }

	@GetMapping("/picture/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Picture picture ) {
		return new ModelMap("picture", picture);
    }

	@PostMapping("/picture/delete")
    public Object delete(@ModelAttribute Picture picture , SessionStatus status) {
        try{
            pictureDao.delete(picture);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
					.addObject("entityId", picture.getLibelle())
					.addObject("entityName", "Picture")
                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/picture/list");
        }
        status.setComplete();
		return "redirect:/picture/list";
    }
}


