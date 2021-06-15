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

import com.gestionnote.dao.FiliereRepository;
import com.gestionnote.dao.PromotionRepository;
import com.gestionnote.dao.RoleDao;
import com.gestionnote.dao.StudentRepository;
import com.gestionnote.entities.Student;
import com.gestionnote.entities.User;

@Controller
public class EtudiantController {
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private FiliereRepository filiereRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    
    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @GetMapping("/students/list")
    public ModelMap student( String value, Model model,Student student){
       
        	
        	  return new ModelMap().addAttribute("student", studentRepository.findAll());
            
       
    }
    


    @GetMapping("/students/form")
    public ModelMap showForm(@RequestParam(value = "id", required = false) User student,Model m ) {
       
    	if (student == null) {    	
        	student = new Student();
        }       
        m.addAttribute("filiere",filiereRepository.findAll());
        m.addAttribute("promotion",promotionRepository.findAll());
        return new ModelMap("student", student);
    }


    @PostMapping("/students/form")
    public String save(@Valid @ModelAttribute("student") Student student, BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
        	
            return "students/form";
        }
        student.setUsername(student.getCin());
        student.setRole(roleDao.findByLibelle("STUDENT").get());
        student.setPsc(student.getCodemassar());
        student.setPassword(bcrypt.encode(student.getCodemassar()));  
        student.setNumappoge(""+student.getCodemassar());
        student.setActived(true);
        studentRepository.save(student);
        status.setComplete();
        return "redirect:/students/list";
    }




    @GetMapping("/students/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) User user ) {
        return new ModelMap("student", user);
    }




    @PostMapping("/students/delete")
    public Object delete(@ModelAttribute Student student , SessionStatus status) {
        try{
            studentRepository.delete(student);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", student.getUsername())
                    .addObject("entityName", "student")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/students/list");
        }
        status.setComplete();
        return "redirect:/students/list";
    }
}
