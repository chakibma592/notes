package com.gestionnote.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.gestionnote.dao.SemestreDescribeRepository;
import com.gestionnote.dao.SemestreRepository;
import com.gestionnote.dao.StudentRepository;
import com.gestionnote.entities.Matiere;
import com.gestionnote.entities.Module;
import com.gestionnote.entities.Notation;
import com.gestionnote.entities.Note;
import com.gestionnote.entities.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

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
	 @Autowired
	    private SemestreDescribeRepository semestreDescribeRepository;
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
	        	System.err.println(errors.toString());
	            return "notes/form";
	        }	
	        
	        noteRepository.save(note);
	        status.setComplete();
	        return "redirect:/notes/form/";
	      
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
		///{idf}/{idp}{ids} @PathVariable("idf") Long idf,@PathVariable("idp") Long idp,@PathVariable("ids") Long ids,
		@GetMapping("/notes/list")
		public ModelMap noteListBySemestreByPromotionByFiliere(Pageable pageable, @RequestParam(name = "value", required = false) String value,
				Model model) {  
			model.addAttribute("filiere", filiereRepository.findAll());
			model.addAttribute("promotion", promotionRepository.findAll());
			model.addAttribute("student", studentRepository.findAll());
			return new ModelMap().addAttribute("semestre", semestreRepository.findAll(pageable));

	    }
		@GetMapping("/notes/loadheader/{idf}/{ids}")
		@ResponseBody
		public String loadStatesByCountry(@PathVariable("idf") Long idf,@PathVariable("ids") Long ids) {
			//Gson gson = new Gson();
			java.util.List<Module> all = semestreDescribeRepository.findByFiliereSemestre(idf, ids);
	        Gson gson = new GsonBuilder().registerTypeAdapter(Module.class, new JsonSerializer <Module>() {
	           @Override
				public JsonElement serialize(Module student, java.lang.reflect.Type type,
					JsonSerializationContext context) {
					JsonObject jsonObject = new JsonObject();
	               
	                jsonObject.addProperty("modulename", student.getModulename());
	                return jsonObject;
				}
	        }).create();
	      
			return gson.toJson(all);
		}
		@GetMapping("/notes/loadnote/{idf}/{ids}/{ide}/{idp}")
		@ResponseBody
		public String loadnote(@PathVariable("idf") Long idf,@PathVariable("ids") Long ids,@PathVariable("ide") int ide,@PathVariable("idp") Long idp) {
			
			java.util.List<Note> all = noteRepository.findByFiliereSemestreEtudiant(idf,ids,ide,idp);
			DecimalFormat df = new DecimalFormat() ;
        	df.setMaximumFractionDigits ( 2 ) ; //arrondi à 2 chiffres apres la virgules
        	df.setMinimumFractionDigits ( 2 ) ;
        	df.setDecimalSeparatorAlwaysShown ( true ) ;
			Gson gson = new GsonBuilder().registerTypeAdapter(Note.class, new JsonSerializer <Note>() {
	        	
	        	@Override
				public JsonElement serialize(Note student, java.lang.reflect.Type type,
					JsonSerializationContext context) {
					JsonObject jsonObject = new JsonObject();
	               
	                jsonObject.addProperty("module", student.getModule().getModulename());
	                jsonObject.addProperty("matiere", student.getMatiere().getMatierename());
	                jsonObject.addProperty("note", student.getNotesession1());
	                String decision="NV";
	                if(student.getNotesession1()>12.0) {
	                	decision="V";
	                }
	                double moyenne=0.0;
	                Iterator it= all.iterator();
	                while(it.hasNext()) {
	                	Note m= (Note)it.next();
	                	moyenne+=m.getNotesession1();
	                }
	                moyenne=moyenne/all.size();
	                jsonObject.addProperty("decision", decision);
	                jsonObject.addProperty("moyenne",df.format(moyenne));
	                return jsonObject;
				}
	        }).create();
	      
			return gson.toJson(all);
		}
	
}
