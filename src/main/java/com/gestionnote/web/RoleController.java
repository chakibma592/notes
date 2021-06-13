																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																						package com.gestionnote.web;

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

import com.gestionnote.dao.RoleDao;
import com.gestionnote.dao.UserDao;
import com.gestionnote.entities.Role;


@Controller
public class RoleController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;



    @GetMapping("/role/list")
    public ModelMap soussecteur( Model model){
        
            return new ModelMap().addAttribute("data", roleDao.findAll());
       
    }



    @GetMapping("/role/form")
    public String showForms(@RequestParam(value = "id", required = false) Role role, Model m) {
        if (role == null) {
        	role = new Role();
        }
        
        m.addAttribute("role", role);
        
        m.addAttribute("user", userDao.findAll());
     
        return "role/form";
        
    }


    @PostMapping("/role/form")
    public String add(@Valid @ModelAttribute("role") Role role, BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "role/form";
        }
        roleDao.save(role);
        status.setComplete();
        return "redirect:/role/list";
    }



    @GetMapping("/role/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Role role) {
        return new ModelMap("soussecteur", role);
    }



    @PostMapping("/role/delete")
    public Object delete(@ModelAttribute Role role, SessionStatus status) {
        try {
            roleDao.delete(role);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    
                    .addObject("entityName", "soussecteur")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink", "/role/list");
        }
        status.setComplete();
        return "redirect:/role/list";
    }
}