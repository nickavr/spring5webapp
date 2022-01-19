package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/authors")
    public String getAuthors(Model model){ //as arguments, what we get from the request made to the server (we re the server)
        model.addAttribute("authors", authorRepository.findAll()); //key value for the html to know how to map the provided data, used in template/authors/list.html
        return "authors/list"; //this looks into resources -> templates -> authors and returns the "list.html"
    }
}
