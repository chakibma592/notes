package com.gestionnote.web;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionnote.dao.AnneeUniversitaireRepository;
import com.gestionnote.dao.FiliereRepository;
import com.gestionnote.dao.MatiereRepository;
import com.gestionnote.dao.ModuleRepository;
import com.gestionnote.dao.NoteRepository;
import com.gestionnote.dao.PromotionRepository;
import com.gestionnote.dao.SemestreRepository;
import com.gestionnote.dao.StudentRepository;
import com.gestionnote.entities.Matiere;
import com.gestionnote.entities.Note;

@Controller
public class NoteController {
	 @Autowired
	    private NoteRepository noteRepository;
	 @Autowired
	    private PromotionRepository promotionRepository;
	 @Autowired
	    private FiliereRepository filiereRepository;
	 @Autowired
	    private StudentRepository studentRepository;
	 @Autowired
	    private SemestreRepository semestreRepository;
	 @Autowired
	    private AnneeUniversitaireRepository yearRepository;
	 @Autowired
	    private ModuleRepository moduleRepository;
	 @Autowired
	    private MatiereRepository matiereRepository;
	 @GetMapping("/notes/form")
	    public ModelMap showForm(@RequestParam(value = "id", required = false) Note note,Model m ) {
	       
	    	if (note == null) {    	
	        	note = new Note();
	        } 
	    	m.addAttribute("note", note);
	        m.addAttribute("filiere",filiereRepository.findAll());
	        m.addAttribute("promotion",promotionRepository.findAll());
	        m.addAttribute("semestre",semestreRepository.findAll());
	        m.addAttribute("module", moduleRepository.findAll());
	        m.addAttribute("matiere",matiereRepository.findAll());
	        m.addAttribute("student",studentRepository.findAll());
	        m.addAttribute("year",yearRepository.findAll());
	        return new ModelMap("note", note);
	    }


	    @PostMapping("/notes/form")
	    public String save(@Valid @ModelAttribute("note") Note note, BindingResult errors, SessionStatus status) {
	        if (errors.hasErrors()) {
	        	
	            return "notes/form";
	        }
	      
	        noteRepository.save(note);
	        status.setComplete();
	        return "redirect:/notes/form";
	    }
		@GetMapping("/listmatieres")
		public @ResponseBody String getStates(@RequestParam Long moduleId)
		{
			String json = null;
			ArrayList<Matiere> list = matiereRepository.findByModule(moduleRepository.findById(moduleId).get());
			try {
				json = new ObjectMapper().writeValueAsString(list);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return json;
		}
}
