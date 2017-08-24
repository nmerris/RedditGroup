package com.example.reddit.controllers;

import com.example.reddit.models.Post;
import com.example.reddit.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class MainController {

    @Autowired
    PostRepository postRepo;

    @RequestMapping("/")
    public String home() {
      return "index";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/newpost")
    public String newPost(Model model)
    {
        model.addAttribute("newPost", new Post());
        return "newpost";
    }

    @PostMapping("/newpost")
    public String processPost(@ModelAttribute ("newPost") Post post)
    {
        post.setDate(new Date());
        postRepo.save(post);
        return "newpost";
    }

    @GetMapping("/userposts")
    public String userPosts()
    {

       return "userposts";
    }

    @RequestMapping("/allposts")
    public String allPosts(Model model)
    {
        model.addAttribute("postList",postRepo.findAll());
        return "allposts";
    }




}
