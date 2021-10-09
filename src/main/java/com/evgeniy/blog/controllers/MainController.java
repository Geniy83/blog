package com.evgeniy.blog.controllers;

import com.evgeniy.blog.models.Post;
import com.evgeniy.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        Iterable<Post> posts = postRepository.findByStatusNotNull();
        model.addAttribute("posts", posts);
        return "about";
    }

}
