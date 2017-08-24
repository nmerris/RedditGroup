package com.example.reddit.controllers;

import com.example.reddit.models.Post;
import com.example.reddit.repositories.PostRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String userPosts(Model model)
    {
       return "userposts";
    }

    @PostMapping("/userposts")
    public String findPosts(Model model, @RequestParam("screennametosearch")String searchname)
    {

        model.addAttribute("postList", postRepo.findAllByScreenNameIs(searchname));
//        System.out.println("#######################################################" + searchname);

        return "allposts";
    }

    @RequestMapping("/allposts")
    public String allPosts(Model model)
    {
        model.addAttribute("postList",postRepo.findAll());
        return "allposts";
    }




}
