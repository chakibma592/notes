package com.gestionnote.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.gestionnote.entities.User;


@Controller
public class UserController {
	
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @GetMapping("/user/list")
    public ModelMap user( String value, Model model,User user){
       
        	
        	  return new ModelMap().addAttribute("user", userDao.findAll());
            
       
    }
    
//    
//    @RequestMapping(value="/user/role{id}", method=RequestMethod.GET)
//    public String detail(Model m, @PathVariable(value="id") int id) {
//    	
//    	
//    	
//    	 User  user = userDao.findById(id).get();
//    	 
//    	 m.addAttribute("title", "USERNAME " +user.getUsername());
//    	
//    	 m.addAttribute("roles", user.getRole());
//    	 
//    	
//    	 m.addAttribute("username", user.getUsername());
//    	 return "user/role";
//    }
//
    

    @GetMapping("/user/form")
    public ModelMap showForm(@RequestParam(value = "id", required = false) User user,Model m ) {
       
    	if (user == null) {    	
        	user = new User();
        }       
    	
        m.addAttribute("role",roleDao.findAll());            
        return new ModelMap("user", user);
    }


    @PostMapping("/user/form")
    public String save(@Valid @ModelAttribute("user") User user, BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
        	
            return "user/form";
        }
        
        user.setPsc(user.getPassword());
        user.setPassword(bcrypt.encode(user.getPassword()));
        
        userDao.save(user);
        status.setComplete();
        return "redirect:/user/list";
    }




    @GetMapping("/user/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) User user ) {
        return new ModelMap("user", user);
    }




    @PostMapping("/user/delete")
    public Object delete(@ModelAttribute User user , SessionStatus status) {
        try{
            userDao.delete(user);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", user.getUsername())
                    .addObject("entityName", "user")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/user/list");
        }
        status.setComplete();
        return "redirect:/user/list";
    }
}


