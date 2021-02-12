package com.gestionreseau.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gestionreseau.ResourceNotFoundException;
import com.gestionreseau.dao.CategoryRepository;
import com.gestionreseau.dao.CommentsRepository;
import com.gestionreseau.dao.PostsRepository;
import com.gestionreseau.entities.Posts;
import com.gestionreseau.entities.Comment;
import com.gestionreseau.entities.Comments;

@RestController
public class CommentsController {
	@Autowired
	private PostsRepository postsRepository ;

    @Autowired
    private CommentsRepository commentsRepository;


	@GetMapping("/comments/list")
	public ModelMap Comments(String value, Model model) {

		return new ModelMap().addAttribute("comments", commentsRepository.findAll());

    }
	@GetMapping("/posts/{postsId}/comments")
	public Page<Comments> getAllCommentsByPostId(@PathVariable(value =  "postsId") Long postsId, Pageable pageable) {
		return commentsRepository.findByPostsId(postsId, pageable);
	}
	@PostMapping("/posts/{postsId}/comments")
	public Comments createComment(@PathVariable(value = "postsId") Long postsId,
								 @Valid @RequestBody Comments comments) {
		return postsRepository.findById(postsId).map(posts -> {
			comments.setPosts(posts);
			return commentsRepository.save(comments);
		}).orElseThrow(() -> new ResourceNotFoundException("PostsId " + postsId + " not found"));
	}


	@GetMapping("/comments/form")
	public String showForms(@RequestParam(value = "id", required = false) Comments comments, Model m) {
		if (comments == null) {
			comments= new Comments();
        }
		m.addAttribute("comments", comments);		
		m.addAttribute("posts", postsRepository.findAll());
		return "comments/form";
    }


	@PostMapping("/comments/form")
	public String add(@Valid @ModelAttribute("comments") Comments comments, BindingResult errors,
			SessionStatus status) {
        if (errors.hasErrors()) {
			return "comments/form";
        }
		commentsRepository.save(comments);
        status.setComplete();
		return "redirect:/comments/list";
    }



	@GetMapping("/comments/delete")
	public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Comments comments) {
		return new ModelMap("comments", comments);
    }



	@PostMapping("/comments/delete")
	public Object delete(@ModelAttribute Comments comments, SessionStatus status) {
        try {
			commentsRepository.delete(comments);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")

                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/comments/list");
        }
        status.setComplete();
		return "redirect:/comments/list";
    }

}
