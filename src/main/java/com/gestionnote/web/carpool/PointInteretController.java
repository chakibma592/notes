																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																						package com.gestionnote.web.carpool;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gestionnote.dao.carpool.NaturePIDao;
import com.gestionnote.dao.carpool.PointInteretDao;
import com.gestionnote.entities.carpool.PointInteret;


@Controller
public class PointInteretController {

    @Autowired
	private NaturePIDao naturepiDao;

    @Autowired
    private PointInteretDao pointInteretDao;


	@GetMapping("/PointInteret/list")
	public ModelMap PointInteret(String value, Model model) {

		return new ModelMap().addAttribute("data", pointInteretDao.findAll());

    }


	@GetMapping("/PointInteret/form")
	public String showForms(@RequestParam(value = "id", required = false) PointInteret pointinteret, Model m) {
		if (pointinteret == null) {
			pointinteret = new PointInteret();
        }
		m.addAttribute("pointinteret", pointinteret);
		m.addAttribute("naturepi", naturepiDao.findAll());
		return "PointInteret/form";
    }


	@PostMapping("/PointInteret/form")
	public String add(@Valid @ModelAttribute("pointinteret") PointInteret pointinteret, BindingResult errors,
			SessionStatus status) {
        if (errors.hasErrors()) {
			return "PointInteret/form";
        }
		pointInteretDao.save(pointinteret);
        status.setComplete();
		return "redirect:/PointInteret/list";
    }



	@GetMapping("/PointInteret/delete")
	public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) PointInteret pointinteret) {
		return new ModelMap("pointinteret", pointinteret);
    }



	@PostMapping("/PointInteret/delete")
	public Object delete(@ModelAttribute PointInteret pointinteret, SessionStatus status) {
        try {
			pointInteretDao.delete(pointinteret);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")

                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/PointInteret/list");
        }
        status.setComplete();
		return "redirect:/PointInteret/list";
    }
}