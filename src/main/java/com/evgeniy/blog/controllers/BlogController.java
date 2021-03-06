package com.evgeniy.blog.controllers;

import com.evgeniy.blog.models.Post;
import com.evgeniy.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
        public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findByStatusNull();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    private String blogPostAdd(@RequestParam String name, @RequestParam String address, @RequestParam String mail, @RequestParam String phone, @RequestParam String full_text, Model model) {
        String status = null;
        Post post = new Post(name, address, mail, phone, full_text, status);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    private String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String address, @RequestParam String mail, @RequestParam String phone, @RequestParam String full_text, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setName(name);
        post.setAddress(address);
        post.setMail(mail);
        post.setPhone(phone);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}/edit1")
    public String blogEdit1(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-edit1";
    }

        @PostMapping("/blog/{id}/edit1")
        private String blogPostUpdate1 ( @PathVariable(value = "id") long id, @RequestParam String status, Model model){
            Post post = postRepository.findById(id).orElseThrow();
            post.setStatus(status);
            postRepository.save(post);
            return "redirect:/blog";
        }

        @PostMapping("/blog/{id}/remove")
        private String blogPostDelete ( @PathVariable(value = "id") long id, Model model){
            Post post = postRepository.findById(id).orElseThrow();
            postRepository.delete(post);
            return "redirect:/blog";
        }
    }
